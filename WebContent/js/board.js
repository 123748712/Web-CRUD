/**
 * 
 */
//목록 가져오기
let tpNum = 0;
function listServer(page){
	$.ajax({
		url:'/List.do',
		type:'post',
		data: {'page' : page},
		success:function(result){
			tpNum = result.tp;
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
			
			// 페이징 BS Pagination, pager 활용
			let pager = `<div class="container">`;
			// <이전> 버튼
			if(result.sp >= 1) {
				pager += `<ul class="pager">`
				pager += `<li><a href="#" class="prev">Prev</a></li></ul>`
			}
		 	pager += `<ul class="pagination pager">`;
			for(let i = result.sp; i<=result.ep; i++) {
				if(currentPage == i) {
					pager += `<li class="active"><a href="#" class="paging">${i}</a></li>`;
				} else {
					pager += `<li><a href="#" class="paging">${i}</a></li>`;
				}
			}
				pager += `</ul>`;
			// <다음> 버튼
			if(result.ep <= result.tp){
				pager += `<ul class="pager">`
				pager += `<li><a href="#" class="next">Next</a></li></ul>`
			}
			pager += `</div>`;
				$('#pageList').html(pager);
		},
		error:function(xhr){
			alert(xhr.status);
		},
		dataType:'json'
	});
}

/* 페이지 번호 클릭 이벤트 */
$(function(){
	$('#pageList').on('click', '.paging', function(){
		currentPage = $(this).text();
		listServer(currentPage);
	});
	/* next 틀릭 이벤트 */
	$('#pageList').on('click', '.next', function(){
		let vnext = $('.paging').last().text();
		if(vnext == tpNum) {
			currentPage = vnext;
		} else {
			currentPage = parseInt(vnext)+1;
		}
		listServer(currentPage);
	});
	/* prev 클릭 이벤트 */
	$('#pageList').on('click', '.prev', function(){
		let vprev = $('.paging').first().text();
		if(vprev == 1) {
			currentPage = vprev;
		} else {
			currentPage = parseInt(vprev)-1;
		}
		listServer(currentPage);
		
	});
});