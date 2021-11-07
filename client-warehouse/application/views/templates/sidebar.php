<!-- Sidebar -->
<ul class="navbar-nav sidebar sidebar-light accordion" id="accordionSidebar">
  <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<?= base_url('dashboard')?>">
    <div class="sidebar-brand-icon">
      <img src="<?= base_url('assets/') ?>img/logo/logo2.png">
    </div>
    <div class="sidebar-brand-text mx-3">WareHouse</div>
  </a>
  <hr class="sidebar-divider my-0">
  <li class="nav-item active">
    <a class="nav-link" href="<?= base_url('dashboard')?>">
      <i class="fas fa-fw fa-tachometer-alt"></i>
      <span>Dashboard</span></a>
  </li>
  <hr class="sidebar-divider">
  <div class="sidebar-heading">
    Features
  </div>
  <li class="nav-item">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseBootstrap" aria-expanded="true" aria-controls="collapseBootstrap">
      <i class="far fa-fw fa-window-maximize"></i>
      <span>Data Master</span>
    </a>
    <div id="collapseBootstrap" class="collapse" aria-labelledby="headingBootstrap" data-parent="#accordionSidebar">
      <div class="bg-white py-2 collapse-inner rounded">
        <!-- <h6 class="collapse-header">Bootstrap UI</h6> -->
        <a class="collapse-item" href="<?= base_url('category')?>">Category</a>
        <a class="collapse-item" href="<?= base_url('measurement')?>">Measurement</a>
        <a class="collapse-item" href="<?= base_url('comodity')?>">Commodity</a>
        <a class="collapse-item" href="<?= base_url('suplier')?>">Supplier</a>
      </div>
    </div>
  </li>
  <!-- <li class="nav-item">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseForm" aria-expanded="true" aria-controls="collapseForm">
      <i class="fab fa-fw fa-wpforms"></i>
      <span>Forms</span>
    </a>
    <div id="collapseForm" class="collapse" aria-labelledby="headingForm" data-parent="#accordionSidebar">
      <div class="bg-white py-2 collapse-inner rounded">
        <h6 class="collapse-header">Forms</h6>
        <a class="collapse-item" href="form_basics.html">Form Basics</a>
        <a class="collapse-item" href="form_advanceds.html">Form Advanceds</a>
      </div>
    </div>
  </li> -->
  <hr class="sidebar-divider">
  <div class="version" id="version-ruangadmin"></div>
</ul>
<div id="content-wrapper" class="d-flex flex-column">
  <div id="content">
    <!-- TopBar -->
    <nav class="navbar navbar-expand navbar-light bg-navbar topbar mb-4 static-top">
      <button id="sidebarToggleTop" class="btn btn-link rounded-circle mr-3">
        <i class="fa fa-bars"></i>
      </button>
      <ul class="navbar-nav ml-auto">
        <div class="topbar-divider d-none d-sm-block"></div>
        <li class="nav-item dropdown no-arrow">
          <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <img class="img-profile rounded-circle" src="<?= base_url('assets/')?>img/boy.png" style="max-width: 60px">
            <span class="ml-2 d-none d-lg-inline text-white small"><?= get_user_data('name')?></span>
          </a>
          <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
            <a class="dropdown-item" href="#">
              <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
              Profile
            </a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="javascript:void(0);" data-toggle="modal" data-target="#logoutModal">
              <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
              Logout
            </a>
          </div>
        </li>
      </ul>
    </nav>

  <!-- Container Fluid-->
      <div class="container-fluid" id="container-wrapper">

          <div class="d-sm-flex align-items-center justify-content-between mb-4">
              <h1 class="h3 mb-0 text-gray-800"><?= $title?></h1>
              <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="<?= base_url('dashboard')?>">Home</a></li>
                  <li class="breadcrumb-item active" aria-current="page"><?= $title?></li>
              </ol>
          </div>
  <!-- Sidebar -->