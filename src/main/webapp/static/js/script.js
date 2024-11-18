function excluirProjeto(id) {
    if (confirm('Tem certeza que deseja excluir este projeto?')) {
        $.ajax({
            url: '/projetos/' + id,
            type: 'DELETE',
            success: function(result) {
                $('#projeto-' + id).remove();
                alert(result)
            },
            error: function(error) {
                alert(error.responseJSON.message)
            }
        });
    }
}

function atualizarProjeto(id) {
    const nome = $('#nome').val();
    const dataInicio = $('#dataInicio').val();
    const previsaoTermino = $('#previsaoTermino').val();
    const dataRealTermino = $('#dataRealTermino').val();
    const orcamento = $('#orcamento').val();
    const descricao = $('#descricao').val();
    const status = $('#status').val();
    const risco = $('#risco').val();
    const gerenteId = $('#gerente').val();

    const projeto = {
        nome: nome,
        dataInicio: dataInicio,
        previsaoTermino: previsaoTermino,
        dataRealTermino: dataRealTermino,
        orcamento: orcamento,
        descricao: descricao,
        status: status,
        risco: risco,
        gerenteId: gerenteId

    };

    $.ajax({
        url: '/api/projetos/' + id,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(projeto),
        success: function (result) {

            alert("Projeto atualizado com sucesso!");
            window.location.href = "/projetos/" + id;
        },
        error: function (error) {
            if (error.responseJSON && error.responseJSON.errors) {
                const errors = error.responseJSON.errors;
                let errorMessage = "";
                for (const field in errors) {
                    errorMessage += errors[field] + "\n";
                }
                alert(errorMessage);
            }
            else {
                alert(error.responseJSON.message);
            }
        }
    });
}