# REST API

- [그런 REST API로 괜찮은가?](https://deview.kr/2017/schedule/212?lang=ko) 를 참고해 Self-Describtive Message와 HATEOAS 제 조건을 만족하는 REST API를 개발
- TDD 포함

# 개발상세

- asciidoctor-maven-plugin 플러그인으로 generated-docs 디렉토리에 index.adoc 템플릿을 이용해 index.html 파일 생성
- maven-resources-plugin 플러그인으로 resource 파일 ${project.build.directory}/generated-docs 파일을 ${project.build.outputDirectory}/static/docs로 옮겨줌 스프링부트 기능으로 서버 실행하면 static/docs/index.html 페이지 접근 가능 