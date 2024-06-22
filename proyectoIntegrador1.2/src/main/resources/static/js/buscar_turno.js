document.getElementById('searchForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const turnoId = document.getElementById('turnoId').value;
    const resultDiv = document.getElementById('result');

    fetch(`/turnos/${turnoId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Turno no encontrado');
            }
        })
        .then(data => {
            resultDiv.innerHTML = `
                <h3>Detalles del Turno:</h3>
                <p>Fecha: ${data.fecha}</p>
                <p>Hora: ${data.hora}</p>
            `;
        })
        .catch(error => {
            resultDiv.innerHTML = `<p class="text-danger">${error.message}</p>`;
        });
});
