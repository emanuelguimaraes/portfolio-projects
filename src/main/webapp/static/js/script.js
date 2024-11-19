function excluirProjeto(id) {
    if (confirm('Tem certeza que deseja excluir este projeto?')) {
        $.ajax({
            url: '/projetos/' + id,
            type: 'DELETE',
            success: function(result) {
                localStorage.setItem('mensagemSucesso', result)
                window.location.href = '/projetos'
            },
            error: function(error) {
                localStorage.setItem('mensagemErro', error.responseJSON.message)
                window.location.href = '/projetos'
            }
        });
    }
}

function atualizarProjeto(id) {
    const nome = $('#nome').val()
    const dataInicio = $('#dataInicio').val()
    const previsaoTermino = $('#previsaoTermino').val()
    const dataRealTermino = $('#dataRealTermino').val()
    const orcamento = $('#orcamento').val()
    const descricao = $('#descricao').val()
    const status = $('#status').val()
    const risco = $('#risco').val()
    const gerenteId = $('#gerente').val()

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
        url: '/projetos/' + id,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(projeto),
        success: function (result) {
            localStorage.setItem('mensagemSucesso', 'Projeto atualizado com sucesso!')
            window.location.href = '/projetos'
        },
        error: function (error) {
            if (error.responseJSON && error.responseJSON.errors) {
                const errors = error.responseJSON.errors
                let errorMessage = ""
                for (const field in errors) {
                    errorMessage += errors[field] + "\n"
                }
                localStorage.setItem('mensagemErro', errorMessage)
            }
            else {
                localStorage.setItem('mensagemErro', error.responseJSON.message)
            }
            window.location.href = '/projetos'
        }
    });
}

function exibirMensagemSucesso() {
    const mensagemSucesso = localStorage.getItem('mensagemSucesso')
    if (mensagemSucesso) {
        const mensagemDiv = $('<div class="alert alert-success" role="alert">' + mensagemSucesso + '</div>')
        $('.container').prepend(mensagemDiv)
        localStorage.removeItem('mensagemSucesso')
    }
}

function exibirMensagemErro() {
    const mensagemErro = localStorage.getItem('mensagemErro')
    if (mensagemErro) {
        const mensagemDiv = $('<div class="alert alert-danger" role="alert">' + mensagemErro + '</div>')
        $('.container').prepend(mensagemDiv)
        localStorage.removeItem('mensagemErro')
    }
}