function excluirProjeto(id) {
    if (confirm('Tem certeza que deseja excluir este projeto?')) {
        fetch(`/projetos/${id}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    localStorage.setItem('mensagemSucesso', 'Projeto excluído com sucesso!');
                    window.location.href = '/projetos';
                } else if (response.status === 400) {
                    return response.json();
                }  else {
                    return Promise.reject("Erro ao excluir o projeto")
                }
            })
            .then(data => {
                if (data) {
                    localStorage.setItem('mensagemErro', data.error);
                    window.location.href = '/projetos';
                }
            })
            .catch(error => {
                localStorage.setItem('mensagemErro', error);
                window.location.href = '/projetos';
            });
    }
}

function atualizarProjeto(id) {
    const nome = document.getElementById('nome').value;
    const dataInicio = document.getElementById('dataInicio').value;
    const previsaoTermino = document.getElementById('previsaoTermino').value;
    const dataRealTermino = document.getElementById('dataRealTermino').value;
    const orcamento = document.getElementById('orcamento').value;
    const descricao = document.getElementById('descricao').value;
    const status = document.getElementById('status').value;
    const risco = document.getElementById('risco').value;
    const gerenteId = document.getElementById('gerente').value;


    const projeto = {
        nome,
        dataInicio,
        previsaoTermino,
        dataRealTermino,
        orcamento,
        descricao,
        status,
        risco,
        gerenteId
    };

    fetch(`/projetos/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(projeto)
    })
        .then(response => {
            if (response.ok) {
                localStorage.setItem('mensagemSucesso', 'Projeto atualizado com sucesso!');
                window.location.href = '/projetos';
            } else if (response.status === 400) {
                return response.json();
            } else {
                return Promise.reject("Ocorreu um erro no servidor");
            }
        })
        .then (data => {
            if (data) {
                let errorMessage = ""
                if (data.errors) {
                    data.errors.forEach(error => errorMessage += error + "\n");
                } else {
                    errorMessage = data.message || "Erro desconhecido";
                }
                localStorage.setItem('mensagemErro', errorMessage)
                window.location.href = '/projetos'
            }
        })
        .catch(error => {
            localStorage.setItem('mensagemErro', error);
            window.location.href = '/projetos';
        });
}

function removerMembro(projetoId, pessoaId) {
    if (confirm('Tem certeza que deseja remover este membro?')) {
        fetch(`/projetos/${projetoId}/membro/${pessoaId}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    localStorage.setItem('mensagemSucesso', 'Membro removido com sucesso!');
                    window.location.href = `/projetos/${projetoId}`;
                } else {
                    return response.json();
                }
            })
            .then(data => {
                if(data) {
                    localStorage.setItem('mensagemErro', data.error)
                    window.location.href=`/projetos/${projetoId}`;
                }
            })
            .catch(error => {
                localStorage.setItem('mensagemErro', 'Erro ao remover membro.');
                window.location.href = `/projetos/${projetoId}`;
            });
    }
}

function exibirMensagem(key, alert) {
    if (localStorage.getItem(key)) {
        const mensagemDiv = document.createElement('div');
        mensagemDiv.classList.add('alert', alert, 'alert-dismissible', 'fade', 'show');
        mensagemDiv.setAttribute('role', 'alert');
        mensagemDiv.id = key;

        const closeButton = document.createElement('button');
        closeButton.type = 'button';
        closeButton.classList.add('btn-close');
        closeButton.setAttribute('data-bs-dismiss', 'alert');
        closeButton.setAttribute('aria-label', 'Close');

        mensagemDiv.textContent = localStorage.getItem(key);
        mensagemDiv.appendChild(closeButton);

        const messageContainer = document.getElementById('message-container');
        if (messageContainer) {
            messageContainer.prepend(mensagemDiv);
        }

        localStorage.removeItem(key);
    }
}


window.addEventListener('load', function() {
    exibirMensagem('mensagemSucesso', 'alert-success');
    exibirMensagem('mensagemErro','alert-danger');
});