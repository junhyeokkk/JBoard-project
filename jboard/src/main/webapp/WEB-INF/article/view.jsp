<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글보기</title>
<link rel="stylesheet" href="/jboard/css/style.css">
<script>
    	window.onload = function(){
    		const commentForm = document.commentForm;
    		const commentList = document.getElementsByClassName('commentList')[0];
    		const commentUpdate = document.getElementsByClassName('commentUpdate');
    		
    	 	for(const btnUpdate of commentUpdate){
    			btnUpdate.style.display ='none';	
    		}
    			
    		let originalText = '';
    		
    		//동적 이벤트 처리
    		document.addEventListener('click', function(e){
    
    			const article = e.target.closest('article');
				const textarea = article.querySelector('textarea');
				
						
   	
				const no = e.target.dataset.no;
				const comment = textarea.value;
				
    			const formData = new FormData();
   
    			
    			formData.append("no", no);
    			formData.append("comment", comment);
    			
    			// 수정완료
    			if(e.target.classList == 'commentUpdate'){
    				e.preventDefault();
    				const btndiv = e.target.closest('div');
    				const Update = btndiv.querySelector('.commentUpdate');	
    				const Modify = btndiv.querySelector('.commentModify');	
    				fetch('/jboard/comment/modify.do', {
    					method: 'POST',
    					body: formData
    				})
    				.then(resp => resp.json())
    				.then(data => {
    					if(data.result > 0){
    						alert('댓글이 수정되었습니다.');
    						
    						textarea.readOnly = true;
            				textarea.style.background = 'transparent';
            				textarea.style.border = 'none';
            				Modify.innerText = '수정';
            				Update.style.display ='none';
    					}else {
    						alert('댓글 수정이 실패 되었습니다.');
    					}
    				
    				})
    				.catch(err => {
    					console.log(err);
    				});	
    			}
    			
    			// 수정 & 취소
    			if(e.target.classList == 'commentModify'){
    				e.preventDefault();
    				
    				const article = e.target.closest('article');
    				const textarea = article.querySelector('textarea');
    				
    				const mode = e.target.innerText;
    				const btndiv = e.target.closest('div');
    				const Update = btndiv.querySelector('.commentUpdate');			

    				if(mode == '수정') {
    					Update.style.display ='inline-block';		
    					originalText = textarea.value;
    					
    					textarea.readOnly = false;
        				textarea.style.background = 'white';
        				textarea.style.border = '1px solid #efefef';
        				textarea.focus();
        				e.target.innerText = '취소';
    				} else {
    					Update.style.display ='none';
    					textarea.value = originalText;
    					textarea.readOnly = true;
        				textarea.style.background = 'transparent';
        				textarea.style.border = 'none';
        				e.target.innerText = '수정';
    				}
    				
    			}
    			
    			if(e.target.classList == 'commentRemove'){
    				e.preventDefault();

    				if(!confirm('정말 삭제 하시겠습니까?')){
        				return;
        				}
        				
        				const no = e.target.dataset.no; // a태그 data-no 속성 값 가져오기
        				const article = e.target.closest('article');
        				
        				fetch('/jboard/comment/delete.do?no='+no)
        					.then(resp => resp.json())
        					.then(data => {
        						if(data.result > 0){
        							alert('댓글이 삭제되었습니다.')
        							
        							//동적 삭제 처리
        							article.remove();
        							
        						} else{
        							alert('댓글이 삭제가 실패되었습니다.')
        						}
        						console.log(data);
        					})
        					.catch(err => {
        						console.log(err);
        					});
    				}
    			
    		});

    		//댓글 등록
    		commentForm.onsubmit = function(e){
    			e.preventDefault();
    			
    			const parent = commentForm.parent.value;
    			const writer = commentForm.writer.value;
    			const comment = commentForm.comment.value;
    			
    			//폼 데이터 생성
    			const formData = new FormData();
    			formData.append("parent", parent);
    			formData.append("writer", writer);
    			formData.append("comment", comment);

    			fetch('/jboard/comment/write.do',{
    				method: 'POST',
    				body: formData
   
    			})
    				.then(resp => resp.json())	
    				.then(data => {
    					
    					if(data != null){
    						alert('댓글 등록');
    						// 등록한 댓글 동적 태그 생성
    						const commentArticle = `
					    						<article class="comment">
					    	                        <span>
					    	                            <span>\${data.rdate}</span>
					    	                            <span>\${data.writer}</span>
					    	                        </span>
					    	                        <textarea name="comment" readonly>\${data.content}</textarea>
					    	                        <div>
						    	                        <a href="#" class="commentRemove" data-no=\${data.no}>삭제</a>
							                            <a href="#" class="commentModify" data-no=\${data.no}>수정</a>
							                            <a href="#" class="commentUpdate" data-no=\${data.no} style='display: none'>수정완료</a>
					    	                        </div>
					    	                    </article>`;
    						commentList.insertAdjacentHTML('beforeend', commentArticle);
    						
    					} else{
    						alert('댓글 등록이 실패했습니다.');
    					}
    					colsole.log(data);
    				})
    				.catch(err => {
    					colsole.log(err);	
    				});
    		}
    		
    	
    	}
    </script>
</head>
<body>
	<div id="container">
		<%@ include file="./_header.jsp"%>
		<main>
			<section class="view">
				<h3>글보기</h3>
				<table>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title"
							value="${articleDto.title}" readonly /></td>
					</tr>
					<c:if test="${articleDto.file > 0}">
						<tr>
							<td>첨부파일</td>
							<c:forEach var="file" items="${articleDto.files}">
								<td><a
									href="/jboard/article/fileDownload.do?fno=${file.fno}">${file.oName}</a>
									<span>${file.download}회 다운로드</span></td>
							</c:forEach>
						</tr>
					</c:if>
					<tr>
						<td>내용</td>
						<td><textarea name="content" readonly>${articleDto.content}</textarea>
						</td>
					</tr>
				</table>
				<div>
					<a href="#" class="btnDelete">삭제</a> <a href="#" class="btnModify">수정</a>
					<a href="/jboard/article/list.do?pg=${pg}" class="btnList">목록</a>
				</div>

				<!-- 댓글리스트 -->
				<section class="commentList">
					<h3>댓글목록</h3>
					<c:forEach var="comment" items="${comments}">
						<article class="comment">
							<span> <span>${comment.rdate}</span> <span>${comment.nick}</span>
							</span>
							<textarea name="comment" readonly>${comment.content}</textarea>
							<c:if test="${sessUser.uid eq comment.writer}">
								<div>
									<!--  HTML 사용자 정의 속성을 이용한 삭제/수정 -->
									<a href="#" class="commentRemove" data-no="${comment.no}">삭제</a>
									<a href="#" class="commentModify" data-no="${comment.no}">수정</a>
									<a href="#" class="commentUpdate" data-no="${comment.no}">수정완료</a>
								</div>
							</c:if>
						</article>
					</c:forEach>
					<c:if test="${empty comments}">
						<p class="empty">등록된 댓글이 없습니다.</p>
					</c:if>
				</section>

				<!-- 댓글입력폼 -->
				<section class="commentForm">
					<h3>댓글쓰기</h3>
					<form name="commentForm">
						<input type="hidden" name="parent" value="${articleDto.no}" /> <input
							type="hidden" name="writer" value="${sessUser.uid}" />
						<textarea name="comment"></textarea>
						<div>
							<a href="#" class="btnCancel">취소</a> <input type="submit"
								class="btnWrite" value="작성완료" />
						</div>
					</form>
				</section>

			</section>
		</main>
		<%@ include file="./_footer.jsp"%>
	</div>
</body>
</html>