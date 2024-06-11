window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_paciente_form');
    let pacientesRegistrados = [];

    // Rellenar formulario con los datos de la URL
    const params = new URLSearchParams(window.location.search);
    document.querySelector('#id').value = params.get('id');
    document.querySelector('#nombre').value = params.get('nombre');
    document.querySelector('#apellido').value = params.get('apellido');
    document.querySelector('#cedula').value = params.get('cedula');
    document.querySelector('#fechaIngreso').value = params.get('fechaIngreso');
    document.querySelector('#calle').value = params.get('calle');
    document.querySelector('#numero').value = params.get('numero');
    document.querySelector('#localidad').value = params.get('localidad');
    document.querySelector('#provincia').value = params.get('provincia');
    document.querySelector('#email').value = params.get('email');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = obtenerDatosFormulario();

        const settings = crearConfiguracionFetch(formData);
        actualizarPaciente(settings);
    });

    function obtenerDatosFormulario() {
        return {
            id: document.querySelector('#id').value,
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
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };
    }

    function actualizarPaciente(settings) {
        fetch(`/pacientes/actualizar`, settings)
            .then(response => response.text())  // Cambia esto para manejar texto plano
            .then(text => {
                console.log(text);  // Muestra la respuesta del servidor
                mostrarMensajeExito(text);
            })
            .catch(error => {
                console.error("Error al actualizar paciente:", error);
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
        } else if (pathname == "/get_pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
