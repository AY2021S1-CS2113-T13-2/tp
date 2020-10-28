//@@author TomLBZ

package visualize;

/**
 * The enum Sprite.
 */
public enum Sprite {

    /**
     * Square sprite.
     */
    SQUARE("+",1,1),
    /**
     * Hollowsquare sprite.
     */
    HOLLOWSQUARE("++++`++++",3,3),

    /**
     * Zero sprite.
     */
    ZERO("++++`++`++`++++",3,5),
    /**
     * One sprite.
     */
    ONE("``+``+``+``+``+",3,5),
    /**
     * Two sprite.
     */
    TWO("++++`+`+`+``+++",3,5),
    /**
     * Three sprite.
     */
    THREE("+++``++++``++++",3,5),
    /**
     * Four sprite.
     */
    FOUR("+`++`++++``+``+",3,5),
    /**
     * Five sprite.
     */
    FIVE("++++``+++``++++",3,5),
    /**
     * Six sprite.
     */
    SIX("++++``++++`++++",3,5),
    /**
     * Seven sprite.
     */
    SEVEN("+++``+`+``+``+`",3,5),
    /**
     * Eight sprite.
     */
    EIGHT("++++`+++++`++++",3,5),
    /**
     * Nine sprite.
     */
    NINE("++++`++++``++++",3,5),

    /**
     * A sprite.
     */
    A("``+```+`+`++++++```++```+",5,5),
    /**
     * B sprite.
     */
    B("++++`+```+++++`+```+++++`",5,5),
    /**
     * C sprite.
     */
    C("`+++`+```++````+```+`+++`",5,5),
    /**
     * D sprite.
     */
    D("+++``+``+`+```++``+`+++``",5,5),
    /**
     * E sprite.
     */
    E("++++++````++++++````+++++",5,5),
    /**
     * F sprite.
     */
    F("++++++````++++++````+````",5,5),
    /**
     * G sprite.
     */
    G("`+++`+````+``+++```+`+++`",5,5),
    /**
     * H sprite.
     */
    H("+```++```+++++++```++```+",5,5),
    /**
     * sprite.
     */
    I("`+++```+````+````+```+++`",5,5),
    /**
     * J sprite.
     */
    J("`+++```+````+````+```++``",5,5),
    /**
     * K sprite.
     */
    K("+```++``+`+++``+``+`+```+",5,5),
    /**
     * L sprite.
     */
    L("+````+````+````+````+++++",5,5),
    /**
     * M sprite.
     */
    M("+```+++`+++`+`++`+`++```+",5,5),
    /**
     * N sprite.
     */
    N("+```+++``++`+`++``+++```+",5,5),
    /**
     * O sprite.
     */
    O("`+++`+```++```++```+`+++`",5,5),
    /**
     * P sprite.
     */
    P("++++`+```+++++`+````+````",5,5),
    /**
     * Q sprite.
     */
    Q("`+++`+```++`+`++``+``++`+",5,5),
    /**
     * R sprite.
     */
    R("`+++`+```+++++`+``+`+```+",5,5),
    /**
     * S sprite.
     */
    S("`+++++`````+++`````+++++`",5,5),
    /**
     * T sprite.
     */
    T("+++++``+````+````+````+``",5,5),
    /**
     * U sprite.
     */
    U("+```++```++```++```+`+++`",5,5),
    /**
     * V sprite.
     */
    V("+```++```+`+`+``+`+```+``",5,5),
    /**
     * W sprite.
     */
    W("+```++`+`++`+`+`+`+``+`+`",5,5),
    /**
     * X sprite.
     */
    X("+```+`+`+```+```+`+`+```+",5,5),
    /**
     * Y sprite.
     */
    Y("+```+`+`+```+````+````+``",5,5),
    /**
     * Z sprite.
     */
    Z("++++++``+```+```+``++++++",5,5),

    /**
     * A sprite.
     */
    a("`+++`````+`+++++``++`++`+",5,5),
    /**
     * B sprite.
     */
    b("++````+````+++``+``+`+++`",5,5),
    /**
     * C sprite.
     */
    c("``````+++`+````+``+``++``",5,5),
    /**
     * D sprite.
     */
    d("```++```+``+++`+``+``++`+",5,5),
    /**
     * E sprite.
     */
    e("``````+++`+``++`++````+++",5,5),
    /**
     * F sprite.
     */
    f("```++``+```+++```+``++```",5,5),
    /**
     * G sprite.
     */
    g("`++``+``+``+++````+``++``",5,5),
    /**
     * H sprite.
     */
    h("```+```+````++``+``++``+`",5,5),
    /**
     * sprite.
     */
    i("``+`````````+````+````++`",5,5),
    /**
     * J sprite.
     */
    j("``+`````````+````+```++``",5,5),
    /**
     * K sprite.
     */
    k("``+````+++``+`+`+`+``+``+",5,5),
    /**
     * L sprite.
     */
    l("```+```+````+```+````++``",5,5),
    /**
     * M sprite.
     */
    m("``````+++`+`+`++`+`++`+`+",5,5),
    /**
     * N sprite.
     */
    n("`````+`++``+``++``+`+```+",5,5),
    /**
     * O sprite.
     */
    o("```````++``+``++``+``++``",5,5),
    /**
     * P sprite.
     */
    p("+`++``+``+`+++``+```+````",5,5),
    /**
     * Q sprite.
     */
    q("`++``+``+``++```+```+++``",5,5),
    /**
     * R sprite.
     */
    r("`````+`++``+``+`+````+```",5,5),
    /**
     * S sprite.
     */
    s("``+++`+`````+`````+`+++``",5,5),
    /**
     * T sprite.
     */
    t("``+``++++``+````+`````++`",5,5),
    /**
     * U sprite.
     */
    u("``````+``++``+`+``+``++`+",5,5),
    /**
     * V sprite.
     */
    v("`````+```+`+`+``+`+```+``",5,5),
    /**
     * W sprite.
     */
    w("`````+`+`++`+`++`+`+`+`+`",5,5),
    /**
     * X sprite.
     */
    x("`````++`++``+````+``++`++",5,5),
    /**
     * Y sprite.
     */
    y("`+``+`+`+```++`+``+``++``",5,5),
    /**
     * Z sprite.
     */
    z("`+++++``+```+```+``+++++`",5,5);

    /**
     * The constant IGNORE.
     */
    public static final String IGNORE = "`";
    /**
     * The constant SPACE.
     */
    public static final String SPACE = " ";
    private final String sprite;
    /**
     * The Width.
     */
    public int width;
    /**
     * The Height.
     */
    public int height;

    Sprite(String sprite, int width, int height) {
        this.sprite = sprite;
        this.width = width;
        this.height = height;
    }

    public String toString() {
        return sprite;
    }
}
