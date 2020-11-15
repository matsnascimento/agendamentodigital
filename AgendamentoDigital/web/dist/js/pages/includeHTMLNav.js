function includeHTMLNav2(menuPai, pagina) {
    var itemMenu = ["home", "empresa", "usuarios", "servicos", "agenda", "categorias"];
    var subitemPagina = ["cadastrarUsuario", "listarFuncionarios", "listarClientes",
        "cadastrarServico", "listarServico",
        "visualizarAgenda"];

    for (i = 0; i < subitemPagina.length; i++) {
        var element = document.getElementById(subitemPagina[i]);
        if (element != null) {
            element.classList.remove("active");
            if (pagina == subitemPagina[i]) {
                element.classList.add("active");
                document.getElementById("menuPai" + menuPai).classList.add("menu-open");
            }
        }
    }

    for (i = 0; i < itemMenu.length; i++) {
        var element = document.getElementById(itemMenu[i]);
        if (element != null) {
            element.classList.remove("active");
            if (menuPai == itemMenu[i]) {
                element.classList.add("active");
            }
        }
    }

}

function includeHTMLNav(menuPai, pagina) {
    var element = document.getElementById(pagina);
    if (element != null) {
        element.classList.add("active");
        document.getElementById("menuPai" + menuPai).classList.add("menu-open");
    }

    var element = document.getElementById(menuPai);
    if (element != null) {
        element.classList.add("active");
    }

}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}