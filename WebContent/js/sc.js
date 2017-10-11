$(document).ready(function() {

	$("#select-sort").click(function() {
		$("#sorting-list").slideToggle(200);
	});
	$("#select-theme").click(function() {
		$("#theme_list").slideToggle(200);
	});
	$("#select-teacher").click(function() {
		$("#teacher_list_choise").slideToggle(200);
	});
	
	$(".show").click(function() {
		$(".login_form").slideToggle(200);
	});
	$(".cabinet_link").click(function() {
		$(".personal_cabinet_menu").slideToggle(200);
	});
	
	$("#registration_form").validate({
		rules : {
			lname : {
				required : true,
				minlength : 4,
				maxlength : 16,

			},
			fname : {
				required : true,
				minlength : 4,
				maxlength : 16,

			},
			login : {
				required : true,
				minlength : 4,
				maxlength : 16,
				 remote:{
              	   type: "POST",
                     url: 'RegistrationLoginChek',
                     delay: 1000,
                     
                },

			},
			password : {
				required : true,
				minlength : 4,
				maxlength : 16

			},
			cpassword : {
				required : true,
				minlength : 4,
				maxlength : 16,
				equalTo : "#password"
			},
			   email:{
                  
                   required:true,
                   email:true ,
                  
                  
               },

		},

		messages : {

			fname : {
				required : "Введите имя!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!"

			},
			lname : {
				required : "Введите фамилию!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!"

			},
			login : {
				required : "Введите логин!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!",
					remote:"Логин занят!"

			},
			password : {
				required : "Введите пароль!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!"

			},
			cpassword : {
				required : "Повтор!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!",
				equalTo : "Пароли не совападают!"
			},

			email : {
				required : "Введите e-mail!",
				email : "Неверный формат!"

			}

		}
	});

	
	$("#registr_teacher").validate({
		rules : {
			lname : {
				required : true,
				minlength : 4,
				maxlength : 16,

			},
			fname : {
				required : true,
				minlength : 4,
				maxlength : 16,

			},
			login : {
				required : true,
				minlength : 4,
				maxlength : 16,
				 remote:{
              	   type: "POST",
                     url: 'RegistrationLoginChek',
                     delay: 1000,
                     
                },

			},
			password : {
				required : true,
				minlength : 4,
				maxlength : 16

			},
			cpassword : {
				required : true,
				minlength : 4,
				maxlength : 16,
				equalTo : "#password"
			},
			   email:{
                  
                   required:true,
                   email:true ,
                  
                  
               },

		},

		messages : {

			fname : {
				required : "Введите имя!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!"

			},
			lname : {
				required : "Введите фамилию!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!"

			},
			login : {
				required : "Введите логин!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!",
					remote:"Логин занят!"

			},
			password : {
				required : "Введите пароль!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!"

			},
			cpassword : {
				required : "Повтор!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!",
				equalTo : "Пароли не совападают!"
			},

			email : {
				required : "Введите e-mail!",
				email : "Неверный формат!"

			}

		}
	});
	$("#add_course_form").validate({
		rules : {
			name : {
				required : true,
				minlength : 4,
				maxlength : 16,

			},
			theme : {
				required : true,
				minlength : 4,
				maxlength : 16,

			},
			start_date : {
				required : true,

			},
			finish_date : {
				required : true,

			},
			

		},

		messages : {

			name : {
				required : "Введите имя!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!"

			},
			theme : {
				required : "Введите фамилию!",
				minlength : "Минимум 4 символа!",
				maxlength : "Максимум 16 символов!"

			},
			start_date : {
				required : "Выбирите дату!",
				

			},
			finish_date : {
				required : "Выбирите дату!",
				

			}
			

			
		}
	});
	
});
