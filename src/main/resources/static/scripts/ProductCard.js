$(function() {
    $(document).on('click', '.buy', function(){
        let clickedButton = $(this);
        let productId = clickedButton.closest('.wrapper').find('a').attr('href').split('/').pop();
        console.log('Product id: ' + productId);
        $.ajax({
            url: '/bucket/add',
            type: 'POST',
            data: { productId: productId },
            success: function(response) {
                //console.log(response);
                clickedButton.closest('.bottom').addClass("clicked");
            },
            error: function(xhr, status, error) {
                console.log(error);
                //window.location.href = '/login';
                $('#myModal').modal('show');
            }
        });
    });

    $(document).on('click', '.remove', function(){
        let clickedButton = $(this);
        let productId = $(this).closest('.wrapper').find('a').attr('href').split('/').pop();
        $.ajax({
            url:'/bucket/remove',
            type: 'POST',
            data: {productId: productId},
            success: function(response){
                //console.log(response);
                clickedButton.closest('.bottom').removeClass("clicked");
            },
            error: function(error){
                console.log(error);
                //window.location.href = '/login';
            }
        })
    });
});


$(function() {
    $('#top').click(function() {
        window.location.href = $(this).parent().find('a').attr('href');
    });
});

function createCards(data,  elementId){
    for(let i = 0; i < data.length; i++){
        let product = data[i];
        let name = $('<h1>' + product.title + '</h1>');
        let clonedName = name.clone();
        let price = $('<p>' + product.price + '₽</p>');
        let cart = $('<p>Added to your cart</p>');

        let wrapper = $('<div class="wrapper"></div>');
        let container = $('<div class="container"></div>');
        let top = $('<a href="/products/' + product.id + '"> <div class="top" style="background: url(' +
            product.imageLink + ') no-repeat center center"></div> </a>');


        let details = $('<div class="details"></div>');
        let details2 = $('<div class="details"></div>');
        details.append(name);
        details.append(price);
        details2.append(clonedName);
        details2.append(cart);

        let left = $('<div class="left"></div>');
        let buy = $('<div class="buy"><i class="material-icons">add_shopping_cart</i></div>');
        left.append(details);
        left.append(buy);

        let right = $('<div class="right"> <div class="done"><i class="material-icons">done' +
            '</i></div> </div>');
        let remove = $('<div class="remove"><i class="material-icons">clear</i></div>');
        right.append(details2);
        right.append(remove);

        let bottom = $('<div class="bottom"></div>');
        bottom.append(left);
        bottom.append(right);

        container.append(top);
        container.append(bottom);

        let inside = $('<div class="inside"> <div class="icon">' +
            '<i class="material-icons">info_outline</i> </div> </div>');
        let contents = $('<div class="contents"></div>');
        contents.append('<p>' + product.description + '</p>');
        inside.append(contents);

        wrapper.append(container);
        wrapper.append(inside);

        document.getElementById(elementId).append(wrapper[0]);
    }
}