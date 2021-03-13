<#macro head basePath="">
  <div class="head">
      <link rel="stylesheet" type="text/css" href="${basePath}/res/css/main.css" />
      <meta charset="UTF-8">
 </div>
</#macro>

<#macro footer basePath="">
  <div class="footer">
     <div><span>footer text</span></div>
                <div id="main-menu" class="main-menu">
                    <ul>
                        <li class="main-menu__item"><a href="${basePath}">Home</a></li>
                        <li class="main-menu__item"><a href="${basePath}/about">about</a></li>
                    </ul>
                </div>

 </div>
</#macro>

<#macro header title="" basePath="">
  <div class="header">

        <img class="banner__photo" th:src="${basePath}res/img/logo.png">
        <div><title>${title}</title></div>

         <nav>
            <div id="main-menu" class="main-menu">
                <ul>
                    <li class="main-menu__item"><a href="${basePath}">Home</a></li>
                    <li class="main-menu__item"><a href="${basePath}/about">about</a></li>
                </ul>
            </div>
         </nav>

 </div>
</#macro>

 <#macro formInput id name label type="text" value="">
   <label for="${id}">${label}</label> 
   <input type="${type}" id="${id}" name="${name}" value="${value}">
</#macro>  
     