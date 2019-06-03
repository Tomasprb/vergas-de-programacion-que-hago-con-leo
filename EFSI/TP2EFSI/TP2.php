<?php
	if (isset($_POST['Mail']))
	{
		$Mail = trim($_POST['Mail']);
	}
	if (isset($_POST['Pass']))
	{
		$Password =trim( $_POST['Pass']);

	}
	if ($Mail=='' || $Password=='')
	{
		echo 'Ambos campos son obligatorios';
	}
	else
	{
		$CantArrobas=substr_count($Mail, '@');
		if ($CantArrobas!=1)
		{
			echo 'solo un arroba antes del dominio, por favor'.'<br />'; 
		}
		else
		{
				$CantEspacios=substr_count($Mail, ' ');
			if ($CantArrobas!=0)
			{
				echo 'Lo sentimos, pero la separacion espacial entre caracteres esta estrictamente prohibida'.'<br />'; 
			}
			else
			{
				$cantidadcaracteres = strlen($Password); 
				if ($cantidadcaracteres <5 || $cantidadcaracteres >10)
				{
					echo 'su contraseña debe ser entre 5 y 10 caracteres'; 
				}
				else
				{
					header('Location: CREAR OTRO PHP BIENVENIDA');
					
				}
			}
		}
		
		
		
		
		
	}

		
?>