<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');


Class Suplier extends CI_Controller{

    public function __construct() {

        parent::__construct();
        if(!$this->session->userdata('loggedin')){
            $this->session->sess_destroy();
            redirect('auth');
        }
     }

     public function index(){
         $result =  $this->api->getResponse('suplier/all','GET', null);
         if($result['status']){

            $data['title'] = 'Suplier Produk';
            $data['suplier'] =  $result['response'];

            $this->template->load('datamaster/v_suplier',$data);
            
         }
         
   }

   public function add(){
      $this->load->library('form_validation');
      is_allowed('role','modify');
      
      $name = $this->input->post('name');
      $address = $this->input->post('address');
      $phone = $this->input->post('phoneNumber');
      $contact = $this->input->post('contactNumber');

      $this->form_validation->set_rules('supplierName', 'Supplier Name', 'required|trim');
		$this->form_validation->set_rules('address', 'Address', 'required|trim');
      if ($this->form_validation->run() == false) {
         $data = [
                  'suplierPK'=>array('name'=> $name), 
                  'address'=>$address,
                  'phoneNumber'=>$phone,
                  'contactNumber'=>$contact
         ];
         $response = $this->api->getResponse('suplier','POST', $data);
         echo json_encode($response);
      }
   }

   public function edit(){
      is_allowed('role','modify');

      $name = $this->input->post('name');
      $address = $this->input->post('address');
      $phone = $this->input->post('phoneNumber');
      $contact = $this->input->post('contactNumber');
      
      $data = [
            'suplierPK'=>array('name'=> $name), 
            'address'=>$address,
            'phoneNumber'=>$phone,
            'contactNumber'=>$contact
      ];
      $response = $this->api->getResponse('suplier','PUT', $data);
      echo json_encode($response);
   }

   public function hapus(){
      is_allowed('role','modify');

      $name = $this->input->post('name');
      $response = $this->api->getResponse('suplier?name='.$name,'DELETE', null);
      echo json_encode($response);
   }
}

?>