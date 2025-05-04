INSERT INTO public.temple(
	 city, 
	created_date_time, 
	is_annadhanam_available,
	is_prasadham_available, 
	official_email, 
	official_phone, 
	other_address, 
	postal_code,
	primary_temple_name, 
	secondary_temple_name,
	special_pooja_date_time,
	state, 
	street_address,
	updated_date_time, 
	user_id)
	VALUES ( 'Chennai', current_timestamp,true,true, 'iyappan.Kaggadasapura@test.com', '+91 9008121936', 
			'Koyembedu', '560016', 'Kabaleeshwarar Kovil', 'Kabaleeshwarar (Sivan) temple', current_timestamp, 
			'Tamil Nadu', 'Swamy Nagar', current_timestamp, 1);
			
select * from public.temple;