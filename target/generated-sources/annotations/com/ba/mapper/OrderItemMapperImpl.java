package com.ba.mapper;

import com.ba.dto.CategoryDTO;
import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.OrderDTO;
import com.ba.dto.OrderItemDTO;
import com.ba.dto.PaymentTypeDTO;
import com.ba.dto.ProductDTO;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Category;
import com.ba.entity.Customer;
import com.ba.entity.Media;
import com.ba.entity.Order;
import com.ba.entity.OrderItem;
import com.ba.entity.PaymentType;
import com.ba.entity.Product;
import com.ba.entity.Waiter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-03T17:09:15+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDTO toDtO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setId( orderItem.getId() );
        orderItemDTO.setProduct( productToProductDTO( orderItem.getProduct() ) );
        orderItemDTO.setPiece( orderItem.getPiece() );
        orderItemDTO.setTotalPrice( orderItem.getTotalPrice() );
        orderItemDTO.setOrder( orderToOrderDTO( orderItem.getOrder() ) );

        return orderItemDTO;
    }

    @Override
    public OrderItem toEntity(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setId( orderItemDTO.getId() );
        orderItem.setProduct( productDTOToProduct( orderItemDTO.getProduct() ) );
        orderItem.setOrder( orderDTOToOrder( orderItemDTO.getOrder() ) );
        orderItem.setPiece( orderItemDTO.getPiece() );
        orderItem.setTotalPrice( orderItemDTO.getTotalPrice() );

        return orderItem;
    }

    @Override
    public List<OrderItem> toEntityList(List<OrderItemDTO> orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( orderItemDTO.size() );
        for ( OrderItemDTO orderItemDTO1 : orderItemDTO ) {
            list.add( toEntity( orderItemDTO1 ) );
        }

        return list;
    }

    @Override
    public List<OrderItemDTO> toDTOList(List<OrderItem> orderItems) {
        if ( orderItems == null ) {
            return null;
        }

        List<OrderItemDTO> list = new ArrayList<OrderItemDTO>( orderItems.size() );
        for ( OrderItem orderItem : orderItems ) {
            list.add( toDtO( orderItem ) );
        }

        return list;
    }

    protected List<ProductDTO> productListToProductDTOList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDTO> list1 = new ArrayList<ProductDTO>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDTO( product ) );
        }

        return list1;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setDescription( category.getDescription() );
        categoryDTO.setProducts( productListToProductDTOList( category.getProducts() ) );
        categoryDTO.setDeleted( category.isDeleted() );

        return categoryDTO;
    }

    protected List<CategoryDTO> categoryListToCategoryDTOList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryDTO> list1 = new ArrayList<CategoryDTO>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategoryDTO( category ) );
        }

        return list1;
    }

    protected ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setProductName( product.getProductName() );
        productDTO.setProductDesc( product.getProductDesc() );
        productDTO.setProductCategory( product.getProductCategory() );
        productDTO.setProductPrice( product.getProductPrice() );
        productDTO.setCategories( categoryListToCategoryDTOList( product.getCategories() ) );
        productDTO.setDeleted( product.isDeleted() );

        return productDTO;
    }

    protected PaymentTypeDTO paymentTypeToPaymentTypeDTO(PaymentType paymentType) {
        if ( paymentType == null ) {
            return null;
        }

        PaymentTypeDTO paymentTypeDTO = new PaymentTypeDTO();

        paymentTypeDTO.setId( paymentType.getId() );
        paymentTypeDTO.setType( paymentType.getType() );

        return paymentTypeDTO;
    }

    protected MediaDTO mediaToMediaDTO(Media media) {
        if ( media == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setId( media.getId() );
        mediaDTO.setName( media.getName() );
        byte[] fileContent = media.getFileContent();
        if ( fileContent != null ) {
            mediaDTO.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }
        mediaDTO.setDeleted( media.isDeleted() );

        return mediaDTO;
    }

    protected CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        if ( customer.getId() != null ) {
            customerDTO.setId( customer.getId() );
        }
        customerDTO.setName( customer.getName() );
        customerDTO.setSurname( customer.getSurname() );
        customerDTO.setPhoneNumber( customer.getPhoneNumber() );
        customerDTO.setAddress( customer.getAddress() );
        customerDTO.setMedia( mediaToMediaDTO( customer.getMedia() ) );

        return customerDTO;
    }

    protected WaiterDTO waiterToWaiterDTO(Waiter waiter) {
        if ( waiter == null ) {
            return null;
        }

        WaiterDTO waiterDTO = new WaiterDTO();

        waiterDTO.setId( waiter.getId() );
        waiterDTO.setName( waiter.getName() );
        waiterDTO.setPhoneNumber( waiter.getPhoneNumber() );
        waiterDTO.setMail( waiter.getMail() );
        waiterDTO.setAddress( waiter.getAddress() );
        waiterDTO.setUrlToImage( waiter.getUrlToImage() );
        waiterDTO.setSalary( waiter.getSalary() );
        waiterDTO.setDeleted( waiter.isDeleted() );

        return waiterDTO;
    }

    protected OrderDTO orderToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getId() );
        orderDTO.setPaymentType( paymentTypeToPaymentTypeDTO( order.getPaymentType() ) );
        orderDTO.setCustomer( customerToCustomerDTO( order.getCustomer() ) );
        orderDTO.setWaiter( waiterToWaiterDTO( order.getWaiter() ) );
        orderDTO.setTotalAmount( order.getTotalAmount() );
        orderDTO.setTotalCount( order.getTotalCount() );
        orderDTO.setOrderDate( order.getOrderDate() );

        return orderDTO;
    }

    protected List<Product> productDTOListToProductList(List<ProductDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDTO productDTO : list ) {
            list1.add( productDTOToProduct( productDTO ) );
        }

        return list1;
    }

    protected Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setDeleted( categoryDTO.isDeleted() );
        category.setName( categoryDTO.getName() );
        category.setDescription( categoryDTO.getDescription() );
        category.setProducts( productDTOListToProductList( categoryDTO.getProducts() ) );

        return category;
    }

    protected List<Category> categoryDTOListToCategoryList(List<CategoryDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Category> list1 = new ArrayList<Category>( list.size() );
        for ( CategoryDTO categoryDTO : list ) {
            list1.add( categoryDTOToCategory( categoryDTO ) );
        }

        return list1;
    }

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setDeleted( productDTO.isDeleted() );
        product.setProductName( productDTO.getProductName() );
        product.setProductDesc( productDTO.getProductDesc() );
        product.setProductCategory( productDTO.getProductCategory() );
        product.setProductPrice( productDTO.getProductPrice() );
        product.setCategories( categoryDTOListToCategoryList( productDTO.getCategories() ) );

        return product;
    }

    protected PaymentType paymentTypeDTOToPaymentType(PaymentTypeDTO paymentTypeDTO) {
        if ( paymentTypeDTO == null ) {
            return null;
        }

        PaymentType paymentType = new PaymentType();

        paymentType.setId( paymentTypeDTO.getId() );
        paymentType.setType( paymentTypeDTO.getType() );

        return paymentType;
    }

    protected Media mediaDTOToMedia(MediaDTO mediaDTO) {
        if ( mediaDTO == null ) {
            return null;
        }

        Media media = new Media();

        media.setId( mediaDTO.getId() );
        media.setDeleted( mediaDTO.isDeleted() );
        media.setName( mediaDTO.getName() );
        byte[] fileContent = mediaDTO.getFileContent();
        if ( fileContent != null ) {
            media.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }

        return media;
    }

    protected Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setName( customerDTO.getName() );
        customer.setSurname( customerDTO.getSurname() );
        customer.setPhoneNumber( customerDTO.getPhoneNumber() );
        customer.setAddress( customerDTO.getAddress() );
        customer.setMedia( mediaDTOToMedia( customerDTO.getMedia() ) );

        return customer;
    }

    protected Waiter waiterDTOToWaiter(WaiterDTO waiterDTO) {
        if ( waiterDTO == null ) {
            return null;
        }

        Waiter waiter = new Waiter();

        waiter.setId( waiterDTO.getId() );
        waiter.setDeleted( waiterDTO.isDeleted() );
        waiter.setName( waiterDTO.getName() );
        waiter.setPhoneNumber( waiterDTO.getPhoneNumber() );
        waiter.setMail( waiterDTO.getMail() );
        waiter.setAddress( waiterDTO.getAddress() );
        waiter.setUrlToImage( waiterDTO.getUrlToImage() );
        waiter.setSalary( waiterDTO.getSalary() );

        return waiter;
    }

    protected Order orderDTOToOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDTO.getId() );
        order.setPaymentType( paymentTypeDTOToPaymentType( orderDTO.getPaymentType() ) );
        order.setCustomer( customerDTOToCustomer( orderDTO.getCustomer() ) );
        order.setWaiter( waiterDTOToWaiter( orderDTO.getWaiter() ) );
        order.setTotalAmount( orderDTO.getTotalAmount() );
        order.setTotalCount( orderDTO.getTotalCount() );
        order.setOrderDate( orderDTO.getOrderDate() );

        return order;
    }
}
