# 게시판 "/boards"

## Body parameter

- "title" : "String" -> 게시판 이름
- createdTime : LocalDateTime -> 생성시간
- posts : List<Post> -> 게시글들

### 생성 Post

- "title" : "String" -> 게시판 이름
- createdTime : LocalDateTime -> 생성시간

### 조회 Get

- id : long -> 게시판 ID

### 수정 "/boards/{boardId}" Put

- id : long -> 게시판 ID
- "title" : "String" -> 게시판 이름

### 삭제 "/boards/{boardId}" Delete

- id : long -> 게시판 ID

# 게시글  "/posts"

## Body parameter

- title : "String" -> 게시글 이름
- content : "String" -> 게시글 내용
- board : Board -> 게시판
- createdTime : LocalDateTime -> 생성시간
- writer: "String" -> 작성자
- comments : List<comment> -> 댓글들

### 생성 Post

- title : "String" -> 게시글 이름
- content : "String" -> 게시글 내용
- id : long -> 게시판 ID
- createdTime : LocalDateTime -> 생성시간

### 조회 Get

- 게시글 ID

### 수정 "/posts/{postId}" Put

- id : long -> 게시판 ID
- writerName : "String" -> 작성자 이름

### 삭제"/posts/{postId}" Delete

- id : long -> 게시판 ID
- writerName : "String" -> 작성자 이름

# 댓글 "/comments"

## Body parameter

- writer : "String" -> 작성자 이름
- content : "String" -> 댓글 내용
- post : Post -> 게시글
- createdTime : LocalDateTime -> 생성시간

### 생성 Post

- writer : "String" -> 작성자 이름
- content : "String" -> 댓글 내용
- postId : long ->게시글 ID

### 조회 Get

- postId : long ->게시글 ID
- commentId : long -> 댓글 ID

### 수정 "/comments/{commentId}" Put

- postId : long -> 게시글 ID
- commentId : long -> 댓글 ID

### 삭제 "/comments/{commentId}" Delete

- postId : long ->게시글 ID
- commentId : long -> 댓글 ID