package site.dogether.docs.util;

public final class DocumentLinkGenerator {

    public static String generateLink(final DocUrl docUrl) {
        return String.format("link:enum/%s.html[%s,role=\"popup\"]", docUrl.fileName, docUrl.title);
    }

    public enum DocUrl {
        CHALLENGE_GROUP_START_AT_OPTION("challenge-group-start-at-option", "그룹 시작일 옵션"),
        CHALLENGE_GROUP_DURATION_OPTION("challenge-group-duration-option", "그룹 진행 기간 옵션"),
        ;

        private final String fileName;
        private final String title;

        DocUrl(final String fileName, final String title) {
            this.fileName = fileName;
            this.title = title;
        }
    }
}
