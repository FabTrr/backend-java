window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');
    const params = new URLSearchParams(window.location.search);
    const turnoId = params.get('id');

    // Obtener datos del turno y precargar el formulario
    if (turnoId) {
        fetch(`/turnos/${turnoId}`)
            .then(response => response.json())
            .then(turno => {
                if (turno) {
                    document.querySelector('#id').value = turno.id;
                    document.querySelector('#pacienteId').value = turno.paciente.id;
                    document.querySelector('#odontologoId').value = turno.odontologo.id;
                    document.querySelector('#fecha').value = turno.fecha;
                    document.querySelector('#hora').value = turno.hora;
                }
            })
            .catch(error => console.error('Error al obtener los datos del turno:', error));
    }

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = obtenerDatosFormulario();

        const settings = crearConfiguracionFetch(formData);
        actualizarTurno(settings);
    });

    function obtenerDatosFormulario() {
        return {
            id: parseInt(document.querySelector('#id').value),
            fecha: document.querySelector('#fecha').value,
            hora: document.querySelector('#hora').value,
            paciente: {
                id: parseInt(document.querySelector('#pacienteId').value)
            },
            odontologo: {
                id: parseInt(document.querySelector('#odontologoId').value)
            }
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
            .then(response => response.text())
            .then(text => {
                console.log(text);
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
