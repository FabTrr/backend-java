window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_paciente');
    let pacientesRegistrados = []; // Array para almacenar pacientes registrados

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = obtenerDatosFormulario();

        if (!esDatoUnico(formData)) {
            mostrarMensajeError('La cédula o email ya está registrado.');
            return;
        }

        const settings = crearConfiguracionFetch(formData);
        registrarPaciente(settings);
    });

    function obtenerDatosFormulario() {
        return {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            cedula: document.querySelector('#cedula').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value
            },
            email: document.querySelector('#email').value
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

    function registrarPaciente(settings) {
        fetch('/pacientes/registrar', settings)
            .then(async response => {
                if (response.ok) {
                    const data = await response.json();
                    pacientesRegistrados.push(data);
                    mostrarMensajeExito();
                } else {
                    if (response.status === 400) {
                        throw new Error('Cédula o email ya registrados. Intente con otros datos.');
                    } else {
                        const errorText = await response.text();
                        throw new Error("Error en la respuesta del servidor: " + errorText);
                    }
                }
            })
            .catch(error => {
                console.error("Error al registrar paciente:", error);
                mostrarMensajeError(error.message);
            });
    }

    function esDatoUnico(formData) {
        for (let paciente of pacientesRegistrados) {
            if (paciente.cedula === formData.cedula || paciente.email === formData.email) {
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
                <strong>Paciente Agregado</strong>
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
        } else if (pathname === "/get_pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
