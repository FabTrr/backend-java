document.getElementById('searchForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const odontologoId = document.getElementById('odontologoId').value;
    const resultDiv = document.getElementById('result');

    fetch(`/odontologos/${odontologoId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Odontólogo no encontrado');
            }
        })
        .then(data => {
            resultDiv.innerHTML = `
                <h3>Detalles del Odontólogo:</h3>
                <p>Nombre: ${data.nombre}</p>
                <p>Apellido: ${data.apellido}</p>
                <p>Matrícula: ${data.matricula}</p>
            `;
        })
        .catch(error => {
            resultDiv.innerHTML = `<p class="text-danger">${error.message}</p>`;
        });
});
