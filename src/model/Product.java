package model;

public class Product
{
    private final int id;
    private final String name;
    private final String description;
    private final double price;
    private final int stock;

    private Product(ProductBuilder builder)
    {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.stock = builder.stock;
    }

    // Getter'lar
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public double getPrice()
    {
        return price;
    }

    public int getStock()
    {
        return stock;
    }

    // Builder iç sınıfı
    public static class ProductBuilder
    {
        private int id;
        private String name;
        private String description;
        private double price;
        private int stock;

        public ProductBuilder setId(int id)
        {
            this.id = id;
            return this;
        }

        public ProductBuilder setName(String name)
        {
            this.name = name;
            return this;
        }

        public ProductBuilder setDescription(String description)
        {
            this.description = description;
            return this;
        }

        public ProductBuilder setPrice(double price)
        {
            this.price = price;
            return this;
        }

        public ProductBuilder setStock(int stock)
        {
            this.stock = stock;
            return this;
        }

        public Product build()
        {
            return new Product(this);
        }
    }
}