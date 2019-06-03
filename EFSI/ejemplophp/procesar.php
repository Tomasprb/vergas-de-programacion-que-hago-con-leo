<?php
	$nombre = $_POST['nombre'];
	
	if($nombre == ''){
		echo 'Debe completar el campo nombre';
	}
	else{
		echo 'Su nombre es: '. $nombre;
	}
?>