package Entity;
import jakarta.persistence.*;

@Entity
public class LikeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn; // 流水序號

    @Column(nullable = false)
    private int orderName; // 購買數量

    @ManyToOne
    @JoinColumn(name = "account", referencedColumnName = "account")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productNo", referencedColumnName = "productNo")
    private Product product;

    private double totalFee;

    private double totalAmount;

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public int getOrderName() {
        return orderName;
    }

    public void setOrderName(int orderName) {
        this.orderName = orderName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
