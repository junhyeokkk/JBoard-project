<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	const success= ${success};
	
	if(success == 100){
		alert('로그인에 실패했습니다. \n다시 한번 아이디, 비밀번호를 확인 하시기 바랍니다.');
	} else if(success == 101){
		alert('정상적으로 로그아웃 되었습니다.');
	} else if(success == 102){
		alert('먼저 로그인을 하셔야 합니다.');
	}
</script>
        <main>
            <section class="login">
                <form action="/jboard/user/login.do" method="POST">
                    <table border="0">
                        <tr>
                            <td><img src="/jboard/images/login_ico_id.png" alt="아이디"></td>
                            <td><input type="text" name="uid" placeholder="아이디 입력"></td>
                        </tr>
                        <tr>
                            <td><img src="/jboard/images/login_ico_pw.png" alt="비밀번호"></td>
                            <td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
                        </tr>
                    </table>
                    <input type="submit" value="로그인" class="btnLogin">
                </form>	
                <div>
                    <h3>회원 로그인 안내</h3>
                    <p>
                        아직 회원이 아니시면 회원으로 가입하세요.
                    </p>
                    <a href="/jboard/user/terms.do">회원가입</a>
                </div>
            </section>
        </main>
<%@ include file="./_footer.jsp" %>