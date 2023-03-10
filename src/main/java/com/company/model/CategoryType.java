package com.company.model;

public enum CategoryType {

RESEARCH_HISTORY("Arastirma-Tarix"),
SCIENCE("Bilim"),
COMIC("Cizgi Roman"),
CHILD_AND_YOUTH("Usag ve genclik"),
LESSONS_TEST_BOOKS("Tapsirig Kitablari"),
RELIGION("Din"),
LITERATURE("Edebiyyat"),
EDUCATION_PREFERENCE("Tehsil ve ÜStunluk"),
PHILOSOPHY("Felsefe"),
FOREIGN_LANGUAGE("Xarici dil"),
HOBBY("Hobi"),
MYTH_LEGEND("mitolji Efsane"),
HUMOR("mizah"),
PRESTIGE_BOOKS("Prestiji kitablar"),
ART_DESIGN("Resm-dizayn"),
AUDIO_BOOKS("Sesli kitablar"),
OTHERS("Basgalari");

    public String getValue() {
        return value;
    }

    private final String value;
    CategoryType(String value) {
        this.value = value;
    }
}
