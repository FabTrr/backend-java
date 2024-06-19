document.getElementById('searchForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const pacienteId = document.getElementById('pacienteId').value;
    const resultDiv = document.getElementById('result');

    fetch(`/pacientes/${pacienteId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Paciente no encontrado');
            }
        })
        .then(data => {
            resultDiv.innerHTML = `
                <h3>Detalles del Paciente:</h3>
                <p>Nombre: ${data.nombre}</p>
                <p>Apellido: ${data.apellido}</p>
                <p>Cedula: ${data.cedula}</p>
                <p>Fecha de Ingreso: ${data.fechaIngreso}</p>
            `;
        })
        .catch(error => {
            resultDiv.innerHTML = `<p class="text-danger">${error.message}</p>`;
        });
});
