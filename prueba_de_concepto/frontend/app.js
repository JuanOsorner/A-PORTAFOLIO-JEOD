document.addEventListener('DOMContentLoaded', () => {
    // Referencias al DOM
    const dropzone = document.getElementById('dropzone');
    const fileInput = document.getElementById('fileInput');
    const loading = document.getElementById('loading');
    const resultsPanel = document.getElementById('resultsPanel');
    const errorContainer = document.getElementById('errorContainer');
    const errorList = document.getElementById('errorList');

    // 1. Manejo de eventos visuales del Drag & Drop
    dropzone.addEventListener('dragover', (e) => {
        e.preventDefault();
        dropzone.classList.add('drag-active'); // Animación de pulso definida en CSS
    });

    dropzone.addEventListener('dragleave', () => {
        dropzone.classList.remove('drag-active');
    });

    dropzone.addEventListener('drop', (e) => {
        e.preventDefault();
        dropzone.classList.remove('drag-active');
        
        if (e.dataTransfer.files.length > 0) {
            procesarArchivo(e.dataTransfer.files[0]);
        }
    });

    // 2. Manejo del clic tradicional para buscar archivo
    dropzone.addEventListener('click', () => fileInput.click());
    fileInput.addEventListener('change', (e) => {
        if (e.target.files.length > 0) {
            procesarArchivo(e.target.files[0]);
        }
    });

    // 3. Lógica principal de comunicación con el Backend
    async function procesarArchivo(file) {
        // Validación temprana en el cliente
        if (!file.name.endsWith('.csv')) {
            alert('Por favor, sube un archivo con extensión .csv');
            return;
        }

        // Cambio de estado visual a "Cargando"
        dropzone.classList.add('hidden');
        loading.classList.remove('hidden');
        resultsPanel.classList.add('hidden'); // Ocultar resultados previos si los hay

        // Preparar la carga (Payload)
        const formData = new FormData();
        formData.append('archivo', file);

        try {
            // Llamada a tu API en FastAPI
            const response = await fetch('http://localhost:8000/api/ahe/cargar-csv', {
                method: 'POST',
                body: formData
            });

            const data = await response.json();

            if (!response.ok) {
                // Lanzar error para que caiga en el catch
                throw new Error(data.detail || 'Error interno del servidor');
            }

            // Si todo sale bien, mostramos el panel
            mostrarResultados(data.resumen);

        } catch (error) {
            console.error("Error en la petición:", error);
            alert(`Fallo en el procesamiento: ${error.message}`);
            
            // Restaurar estado visual inicial si hay error crítico
            loading.classList.add('hidden');
            dropzone.classList.remove('hidden');
        }
    }

    // 4. Renderizado de resultados
    function mostrarResultados(resumen) {
        loading.classList.add('hidden');
        resultsPanel.classList.remove('hidden'); // Esta clase dispara la animación fade-in del CSS

        // Llenar métricas
        document.getElementById('countSuccess').textContent = resumen.exitosos;
        document.getElementById('countErrors').textContent = resumen.errores;

        // Limpiar lista de errores anterior
        errorList.innerHTML = ''; 

        if (resumen.errores > 0) {
            errorContainer.classList.remove('hidden');
            
            resumen.detalles_errores.forEach(err => {
                const li = document.createElement('li');
                li.className = 'px-4 py-3 text-red-600 bg-red-50/50';
                li.textContent = err;
                errorList.appendChild(li);
            });
        } else {
            errorContainer.classList.add('hidden');
        }
    }
});