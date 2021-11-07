<?php

if(!function_exists('get_user_data')) {
	function get_user_data($field_name = '', $value = '') {
		$ci =& get_instance();
		$set = $ci->session->userdata($field_name);
		if ($field_name == 'role') {
			
			$cn = count($set);
			for ($i=0; $i < $cn; $i++) { 
				# code...
				$data[] = $set[$i]->authority;
			}
			if (in_array($value, $data,TRUE))
			{
				return true;
			}
			else
			{
				return false;
			}
		}else{
            return $set;
        }

		return false;
	}
}

function is_allowed($field_name = '', $value = ''){
	$ci =& get_instance();
	$set = $ci->session->userdata($field_name);
	$cn = count($set);
	for ($i=0; $i < $cn; $i++) { 
		# code...
		$data[] = $set[$i]->authority;
	}
	if (in_array($value, $data,TRUE))
	{
		return true;
	}
	else
	{
		$ci->session->set_flashdata('f_message', 'Sorry you do not have permission to access ');
		$ci->session->set_flashdata('f_type', 'warning');
		redirect('dashboard','refresh');
	}
}