<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');


Class Measurement extends CI_Controller{

    public function __construct() {
        parent::__construct();
        if(!$this->session->userdata('loggedin')){
            redirect('auth');
        }
     }

     public function index(){
      $result =  $this->api->getResponse('measurement/all','GET', null);
      if($result['status']){

         $data['title'] = 'Measurement Produk';
         $data['measurement'] =  $result['response'];

         $this->template->load('datamaster/v_measurement',$data);
         
      }
   }

     public function add(){
         is_allowed('role','modify');
         $name = $this->input->post('name');
         $nick = $this->input->post('nick');
         $desc = $this->input->post('desc');
         
         $data = [
                  'measurementPK'=>array('name'=> $name), 
                  'nickName'=>$nick,
                  'description'=>$desc
         ];
         $response = $this->api->getResponse('measurement','POST', $data);
         echo json_encode($response);
     }

     public function edit(){
        is_allowed('role','modify');
        $name = $this->input->post('name');
         $nick = $this->input->post('nick');
         $desc = $this->input->post('desc');
         
         $data = [
                  'measurementPK'=>array('name'=> $name), 
                  'nickName'=>$nick,
                  'description'=>$desc
         ];
         $response = $this->api->getResponse('measurement','PUT', $data);
         echo json_encode($response);
     }

     public function hapus(){
         is_allowed('role','modify');
         $name = $this->input->post('name');
         $response = $this->api->getResponse('measurement?name='.$name,'DELETE', null);
         echo json_encode($response);
     }
}

?>