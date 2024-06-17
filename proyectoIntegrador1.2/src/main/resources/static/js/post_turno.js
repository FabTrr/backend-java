window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_turno');
    let turnosRegistrados = []; //array para almacenar turnos registrados

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = obtenerDatosFormulario();

        if (!esDatoUnico(formData)) {
            mostrarMensajeError('El turno ya está registrado.');
            return;
        }

        const settings = crearConfiguracionFetch(formData);
        registrarTurno(settings);
    });

    function obtenerDatosFormulario() {
        return {
            paciente: {
                id: document.querySelector('#pacienteId').value
            },
            odontologo: {
                id: document.querySelector('#odontologoId').value
            },
            fecha: document.querySelector('#fecha').value,
            hora: document.querySelector('#hora').value
        };
    }

    function crearConfiguracionFetch(formData) {
        return {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };
    }

    function registrarTurno(settings) {
        fetch('/turnos/registrar', settings)
            .then(async response => {
                if (!response.ok) {
                    const errorText = await response.text();
                    throw new Error("Error en la respuesta del servidor: " + errorText);
                }
                const data = await response.json();
                turnosRegistrados.push(data); //agregar turno registrado a la lista
                mostrarMensajeExito();
            })
            .catch(error => {
                if (error.message.includes('404')) {
                    mostrarMensajeError('ID de paciente u odontólogo no encontrado.');
                } else {
                    console.error("Error al registrar turno:", error);
                    mostrarMensajeError('Error al comunicarse con el servidor.');
                }
            });
    }

    function esDatoUnico(formData) {
        for (let turno of turnosRegistrados) {
            if (turno.paciente.id === formData.paciente.id &&
                turno.odontologo.id === formData.odontologo.id &&
                turno.fecha === formData.fecha &&
                turno.hora === formData.hora) {
                return false;
            }
        }
        return true;
    }

    function mostrarMensajeExito() {
        const divRespuesta = document.querySelector('#response');
        divRespuesta.style.display = "block";
        divRespuesta.innerHTML = `
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Turno Agregado</strong>
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