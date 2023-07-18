$(document).on('click',function (e) {
    let target = e.target;
    //validate log in
    if (target.closest('.log-in-btn')) {
        let emailFormat = new RegExp(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/);

        let isValid = true;
        let logInEmail = $('.log-in-email');
        let logInEmailValue = logInEmail.val();
        let logInEmailInvalid = $('.log-in-email').next();
        let logInPassword = $('.log-in-password');
        let logInPasswordValue = logInPassword.val();
        let logInPasswordInvalid = $('.log-in-password').next();

        if (logInEmailValue === "") {
            logInEmailInvalid.css('display', 'block');
            logInEmailInvalid.html('Vui lòng nhập email');
            isValid = false;
        } else if (!emailFormat.test(logInEmailValue)) {
            logInEmailInvalid.css('display', 'block');
            logInEmailInvalid.html('Email không hợp lệ');
            isValid = false;
        }
        if (logInPasswordValue.length < 6 || logInPasswordValue.length > 20) {
            logInPasswordInvalid.css('display', 'block');
            logInPasswordInvalid.html('Độ dài mật khẩu từ 6 - 20 ký tự')
            isValid = false;
        }

        if (isValid === true) {
            req = {
                email: logInEmailValue,
                password: logInPasswordValue

            }
            $.ajax({
                url: "/api/v1/authentication/login",
                type: "POST",
                data: JSON.stringify(req),
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    alert("thanh cong");



                    window.location.href = 'http://localhost:8080/index';

                },
                error: function(data) {
                    alert("ko thanh cong")

                },
            })

        }

    }
})