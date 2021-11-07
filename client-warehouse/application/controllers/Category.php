<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');


Class Category extends CI_Controller{

    public function __construct() {
        parent::__construct();
        if(!$this->session->userdata('loggedin')){
            $this->session->sess_destroy();
            redirect('auth');
        }
     }

     public function index(){
         $result =  $this->api->getResponse('category/all','GET', null);
         if($result['status']){

            $data['title'] = 'Category Produk';
            $data['category'] =  $result['response'];

            $this->template->load('datamaster/v_category',$data);
            
         }
         
   }

   public function add(){
      
      is_allowed('role','modify');

      $name = $this->input->post('name');
      $desc = $this->input->post('desc');
      
      $data = [
               'categoryPK'=>array('name'=> $name), 
               'description'=>$desc
      ];
      $response = $this->api->getResponse('category','POST', $data);
      echo json_encode($response);
   }

   public function edit(){
      is_allowed('role','modify');

      $name = $this->input->post('name');
      $desc = $this->input->post('desc');
      
      $data = [
               'categoryPK'=>array('name'=> $name), 
               'description'=>$desc
      ];
      $response = $this->api->getResponse('category','PUT', $data);
      echo json_encode($response);
   }

   public function hapus(){
      is_allowed('role','modify');

      $name = $this->input->post('name');
      $response = $this->api->getResponse('category?name='.$name,'DELETE', null);
      echo json_encode($response);
   }
}

?>