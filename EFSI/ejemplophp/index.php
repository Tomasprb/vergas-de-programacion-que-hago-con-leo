<?php 
	include('funciones.php');
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Title of the document</title>
	 <script src="jquery-3.4.1.js" type="text/javascript"></script>
</head>
<body>
	<?php include_once('cabecera.php'); ?>
	<form action="procesar.php" method="post" id="formulario">
		<label>Nombre</label>
		<input type="text" name="nombre" id="nombre" />
		<input type="button" value="Enviar" onclick="Validar();" />
	</form>	
	<?php include_once('cabecera.php'); ?>
	
	<script>
		function Validar(){
			var nombre = $('#nombre').val();
			
			if(nombre==''){
				alert('Debe completar el nombre');
			}
			else{
				$('#formulario').submit();
			}
						
		}
	</script>
</body>

</html>