<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');


Class Comodity extends CI_Controller{

    public function __construct() {
        parent::__construct();
        if(!$this->session->userdata('loggedin')){
            $this->session->sess_destroy();
            redirect('auth');
        }
     }

     public function index(){
         $result =  $this->api->getResponse('comodity/all','GET', null);
         
         if($result['status']){

            $data['title'] = 'Comodity Produk';
            $data['comodity'] =  $result['response'];

            $this->template->load('datamaster/v_comodity',$data);
            
         }
         
   }

   public function get_category(){
      $result =  $this->api->getResponse('category/all','GET', null);
      foreach ($result['response'] as $key => $value) {
         # code...
         $data[] = [
            'id' =>  $value->categoryPK->name,
            'text' => $value->description
         ];
      }
      echo json_encode($data);
   }

   public function get_measurement(){
      $result =  $this->api->getResponse('measurement/all','GET', null);
      foreach ($result['response'] as $key => $value) {
         # code...
         $data[] = [
            'id' =>  $value->measurementPK->name,
            'text' => $value->nickName
         ];
      }
      echo json_encode($data);
   }

   public function add(){
      
      is_allowed('role','modify');

      $name = $this->input->post('name');
      $unit = $this->input->post('unit');
      $measure = $this->input->post('measure');
      $category = $this->input->post('category');
      // $this->form_validation->set_rules('comodityName', 'comodity Name', 'required|trim');
		// $this->form_validation->set_rules('comodityUnit', 'comodity Unit', 'required|trim');
      // if ($this->form_validation->run() == true) {
         $data = [
                  'category'=>$category,
                  'comodityPK'=>array('name'=> $name), 
                  'measurementName'=>$measure,
                  'unit'=>$unit,
                  
         ];
         $response = $this->api->getResponse('comodity','POST', $data);
         echo json_encode($response);
      // }
   }

   public function edit(){
      is_allowed('role','modify');

      $name = $this->input->post('name');
      $unit = $this->input->post('unit');
      $measure = $this->input->post('measure');
      $category = $this->input->post('category');
      // $this->form_validation->set_rules('comodityName', 'comodity Name', 'required|trim');
		// $this->form_validation->set_rules('comodityUnit', 'comodity Unit', 'required|trim');
      // if ($this->form_validation->run() == true) {
         $data = [
            'category'=>$category,
            'comodityPK'=>array('name'=> $name), 
            'measurementName'=>$measure,
            'unit'=>$unit,
         ];
         $response = $this->api->getResponse('comodity','PUT', $data);
         echo json_encode($response);
      // }
   }

   public function hapus(){
      is_allowed('role','modify');

      $name = $this->input->post('name');
      $response = $this->api->getResponse('comodity?name='.$name,'DELETE', null);
      echo json_encode($response);
   }
}

?>