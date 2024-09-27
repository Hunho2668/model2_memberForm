package service;

public class ActionForward { // 클래스명도 'ActionForward'로 수정

    private boolean redirect; // 포워딩 방식
    private String path; // 포워딩 페이지명 설정

    public boolean isRedirect() { // 'blooean'을 'boolean'으로 수정
        return redirect;
    }

    public void setRedirect(boolean redirect) { // 'blooean'을 'boolean'으로 수정
        this.redirect = redirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
