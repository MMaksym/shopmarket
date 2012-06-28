<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(document).ready(function () {
        var form = $("#searchForm");

        var ajaxButton = $("#ajaxButton");
        ajaxButton.click(function () {
            $.ajax({
                type:'post',
                dataType: 'json',
                url:"search/ajax",
                data: form.serialize(),
                success:function (json) {
                    alert("success");
                }
            });

        });
    });
</script>

<form id="searchForm" action="<%=request.getContextPath()%>/search" method="post">
    <input type="text" name="key">
    <input type="submit" value="Find">
    <input id="ajaxButton" type="button" value="AJAX">
</form>

<div id="searchResult">
    <c:forEach var="facet" items="${res.facetFields}">
        <c:forEach var="item" items="${facet.values}">
            ${item.name} ${item.count}<br/>
        </c:forEach>
    </c:forEach>

    <br/>

    <c:forEach var="document" items="${res.results}">
        <div class="product-item">
            <b>${document["prod_name"]}
            ${document['prod_price']} ${document['currency']}</b>
        </div>
    </c:forEach>
</div>

<div id="ajaxResult">

</div>