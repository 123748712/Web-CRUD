/**
 * 
 */
//목록 가져오기
function listServer(page){
	$.ajax({
		url:'/List.do',
		type:'post',
		data: {'page' : page},
		success:function(result){
			//BS-accordion게시판 예제 활용
			let code = `<div class="panel-group" id="accordion">`;
			//여기부터 반복
			$.each(result.data, function(i,v){
			    code += `<div class="panel panel-default">`;
			    code += `  <div class="panel-heading">`;
			    code += `    <h4 class="panel-title">`;
			    code += `      <a data-toggle="collapse" data-parent="#accordion" href="#collapse${v.num}">${v.subject}</a>`;
			    code += `    </h4>`;
			    code += `  </div>`;
			    code += `  <div id="collapse${v.num}" class="panel-collapse collapse">`;
			    code += `    <div class="panel-body">`;
				code += `<div class='p1'>작성자 : ${this.writer}&nbsp;&nbsp;&nbsp;&nbsp; 조회수 : ${v.hit}&nbsp;&nbsp;&nbsp;&nbsp; 날짜 : ${result.data[i].wdate}</div>`;
				code += `<div class='p2'>`;
				code += `  <input type="button" name="modify" value="수정" class="btn btn-warning btn-sm">`;
				code += `  <input type="button" name="delete" value="삭제" class="btn btn-danger btn-sm">`;
				code +=	`</div>`;
				code += `<div class='p3'>${result.data[i].content}</div>`;
				code += `<div class='p4'>댓글구간</div>`;
				code += `			</div>`;
			    code += `  </div>`;
			    code += `</div>`;
			});
			//여기까지...
			code += `</div>`;
			$('#list').html(code);
		},
		error:function(xhr){
			alert(xhr.status);
		},
		dataType:'json'
	});
}