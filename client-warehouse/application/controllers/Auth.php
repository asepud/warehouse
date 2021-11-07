<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

Class Auth extends CI_Controller{

    public function __construct() {
        parent::__construct();
     }

    public function index(){
        $this->load->view('auth/login');
    }

    public function cek_login(){
        $username = $this->input->post('username');
        $password = $this->input->post('password');
        
        $data = [
                'username'=>$username, 
                'password'=>$password
        ];
        
        $result =  $this->api->getResponse('user/authenticate','POST', $data);
        $datas=array();
        if($result['status']){
            
            $datas = array(
                    'token' => $result['response']->jwttoken,
                    'role' => $result['response']->userdetail->authorities,
                    'name' => $result['response']->userdetail->username,
                    'loggedin' => TRUE
                );
                // var_dump($datas);
            $this->session->set_userdata($datas);

            echo json_encode($result);

        }else{
            echo json_encode($result);
        }
       
        
    }

    public function logout(){
        $this->session->sess_destroy();
        redirect($this->index());
    }
}