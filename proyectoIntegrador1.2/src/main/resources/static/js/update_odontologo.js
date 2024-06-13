window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_odontologo_form');

    // Rellenar formulario con los datos de la URL
    const params = new URLSearchParams(window.location.search);
    document.querySelector('#id').value = params.get('id');
    document.querySelector('#nombre').value = params.get('nombre');
    document.querySelector('#apellido').value = params.get('apellido');
    document.querySelector('#numeroMatricula').value = params.get('numeroMatricula');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = obtenerDatosFormulario();
        const settings = crearConfiguracionFetch(formData);
        actualizarOdontologo(settings);
    });

    function obtenerDatosFormulario() {
        return {
            id: document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            numeroMatricula: document.querySelector('#numeroMatricula').value // Asegurarse de capturar la matrícula
        };
    }

    function crearConfiguracionFetch(formData) {
        return {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };
    }

    function actualizarOdontologo(settings) {
        fetch(`/odontologos/actualizar`, settings)
            .then(response => response.text())
            .then(text => {
                console.log(text);  // respuesta del servidor
                mostrarMensajeExito(text);
            })
            .catch(error => {
                console.error("Error al actualizar odontólogo:", error);
                mostrarMensajeError(error.message);
            });
    }

    function mostrarMensajeExito(mensaje) {
        const divRespuesta = document.querySelector('#response');
        divRespuesta.style.display = "block";
        divRespuesta.innerHTML = `
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>${mensaje}</strong>
            </div>`;
    }

    function mostrarMensajeError(mensaje) {
        const divRespuesta = document.querySelector('#response');
        divRespuesta.style.display = "block";
        divRespuesta.innerHTML = `
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Error: ${mensaje}</strong>
            </div>`;
    }
});
