window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');

    // Rellenar formulario con los datos de la URL
    const params = new URLSearchParams(window.location.search);
    document.querySelector('#id').value = params.get('id');
    document.querySelector('#pacienteId').value = params.get('pacienteId');
    document.querySelector('#odontologoId').value = params.get('odontologoId');
    document.querySelector('#fecha').value = params.get('fecha');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = obtenerDatosFormulario();

        const settings = crearConfiguracionFetch(formData);
        actualizarTurno(settings);
    });

    function obtenerDatosFormulario() {
        return {
            id: parseInt(document.querySelector('#id').value), // Asegurar que el ID sea un entero
            pacienteId: parseInt(document.querySelector('#pacienteId').value),
            odontologoId: parseInt(document.querySelector('#odontologoId').value),
            fecha: document.querySelector('#fecha').value
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

    function actualizarTurno(settings) {
        fetch(`/turnos/actualizar`, settings)
            .then(response => response.text())  // Manejo de respuesta en texto plano
            .then(text => {
                console.log(text);  // Muestra la respuesta del servidor
                mostrarMensajeExito(text);
            })
            .catch(error => {
                console.error("Error al actualizar turno:", error);
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

    (function() {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/get_turnos.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
