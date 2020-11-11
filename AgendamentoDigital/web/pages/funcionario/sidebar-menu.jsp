
<!-- Navbar -->
<nav class="navbar navbar-expand navbar-dark main-header">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="${site}/Funcionario/Home" class="nav-link">Home</a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a href="${site}/Funcionario/MinhaConta" class="nav-link">Minha Conta</a>
        </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
        <li class="nav-item d-none d-sm-inline-block">
            <a href="#" class="nav-link">Contato</a>
        </li>
        <li class="nav-item d-none d-sm-inline-block">
            <a class="nav-link" href="${site}/ControleAcesso?acao=Sair">Sair</a>
        </li>
    </ul>
</nav>
<!-- /.navbar -->

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="home.jsp" class="brand-link">
        <img src="${site}/dist/img/AdminLTELogo.png" alt="Agendamento Digital" class="brand-image img-circle elevation-3"
             style="opacity: .8">
        <span class="brand-text font-weight-light">Agendamento Digital</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="https://ui-avatars.com/api/?rounded=true&background=fff&color=0a5&name=${funcionario.nome}" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a href="${site}/Funcionario/MinhaConta" class="d-block">${funcionario.nome}</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item">
                    <a href="${site}/Funcionario/Home" id="home" class="nav-link">
                        <i class="nav-icon fas fa-home"></i>
                        <p>
                            Home
                        </p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${site}/Funcionario/AgendarServico" id="agendarServico" class="nav-link">
                        <i class="fas fa-calendar-plus nav-icon"></i>
                        <p>Agendar Servi�o</p>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>