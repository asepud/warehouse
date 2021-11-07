<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

Class Dashboard extends CI_Controller{

    public function __construct() {
        parent::__construct();
        if(!$this->session->userdata('loggedin')){
            redirect('auth');
        }
     }

    public function index(){
         $data['title'] = 'Warehouse';
        $this->template->load('dashboard/v_dashboard',$data);
    }
}