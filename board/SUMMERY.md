1. HomeController에서 index 작성 - resource(index.html)
   : 실행되고 버튼보임. 버튼 누르면 에러.
   2. BoardController에서 save 작성 (get)- resource(save.html) (get post)
   : 실행되고 버튼보임. 글작성 버튼 누르면 save페이지 이동 완료.

3. BoardController에서  save작성(post) - DTO (데이터 전송할때 사용하는 객체) 작성

4. BoardController에서  save작성(post) 마저 작성 (@ModelAttribute)
- save.html의 name들과 DTO의 필드값이 동일하다면, spring이 알아서 호출하면서 담아줌.

5. BoardEntity 작성 <~26분>
- DB와 직접적인 연관성, DB의 테이블 역할을 하는 클래스 (service와 repo 사이에서만)
- @Column

6. BaseEntity 작성 <26 ~ 29>
- 시간을 같이 사용하기 위해

7. repository.class 먼저 작성  <30:30~>
- BoardRepository에서 extends JpaRepository 와 <BoardEntity(엔티티클래스이름), 엔티티클래스가 가지고있는 pk의 팩?>

8. BoardController에서 service 호출 <30:40~>
- RequiredArgsConstructor
- private final BoardService boardService	// 생성자 주입방식으로 의존성 주입
- save->post에 boardService.save(boardDTO) 추가

9. BoardService 작성 <31:40~35:46>
- private final BoardRepository boardRepository; 의존성 주입
- repository는 기본적으로 엔티티 클래스만 받아준다.
  -> service에서 boardRepository에서 save를 호출 -> 그래야 insert를 DB에 할 수 있음.

* DTO -> Entity 변환 과정은 Entity class에서
  Entity -> DTO 변환 과정은 DTO Class에서


10. Entity 클래스에 [9번 보드서비스]에서 작성한 save의 내용을 넘겨줘야 한다.
- 빌더로 변환해서 넘겨주는 경우도 있고,
- 서비스 클래스단에 변환하는 메소드 두고 거기서 변환하는 방법도 있고. 여러가지 방법이 있다.
- public static BoardEntity toSaveEntity(BoardDTO boardDTO 작성
  // DTO에 담긴 값을 Entity에 옮겨 담는다.
  //BoardDTO에 담겨온(save html에서 입력한 값) 것을 Entity의 boardWriter로 set한다는 뜻.


11. BoardService 추가 수정
- public void save(BoardDTO boardDTO) { 내부에 작성
  -> BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
  // 이 메소드객체(BoardEntity.toSaveEntity(boardDTO);)를 실행한 결과를 엔티티 객체(boardEntity)로 받아올 수 있고, 결국 Entity를 save 메소드로 넘겨준다.
  -> boardRepository.save();의 save 내부에 boardRepository.save(boardEntity); 로 작성
  // 이러면 insert 쿼리가 나가게 된다.

12. 이렇게 save가 되고 나면 return값을 index로 보낸다.
    (BoardController 부분)



[//]: # (여기까지 SAVE 파트)

