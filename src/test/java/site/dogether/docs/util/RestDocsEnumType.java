package site.dogether.docs.util;

public interface RestDocsEnumType {

    String getDescription();
    String getRequestValue();

    static void checkDocsValueCountIsEqualToEnumValueCount(final int enumValueCount, final int enumDocsValueCount) {
        if (enumDocsValueCount != enumValueCount) {
            throw new IllegalStateException("Enum의 값 개수와 문서의 값 개수가 일치하지 않습니다.");
        }
    }
}
