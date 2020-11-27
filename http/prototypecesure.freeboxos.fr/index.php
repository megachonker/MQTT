<html lang="en">
    
    <head>
        
        <meta charset="UTF-8">
  
        <link rel="apple-touch-icon" type="image/png" href="https://www.iconnect.fr/wp-content/uploads/2018/04/LOGO-WIFI-BLANC.png">
    
        <meta name="apple-mobile-web-app-title" content="CodePen">
        
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

        <link rel="shortcut icon" type="image/x-icon" href="https://www.iconnect.fr/wp-content/uploads/2018/04/LOGO-WIFI-BLANC.png">

        <link rel="stylesheet" href="./stylesheet/style.css">

        <title>Prise WiFi - MQTT</title>

        <script>
            window.console = window.console || function(t) {};
        </script>

        <script>
            if (document.location.search.match(/type=embed/gi)) {
                window.parent.postMessage("resize", "*");
            }
        </script>

    </head>
    

    <body>
 
    <div class="englobe">

 
    <div class="bouton1">
        
        <p class="textebouton"> Prise Wi-Fi 1</p>

        <div class="power-switch">
            <input type="checkbox" value="Démarrer la machine">
            <div class="button">
                <svg class="power-off">
                    <use xlink:href="#line" class="line"></use>
                    <use xlink:href="#circle" class="circle"></use>
                </svg>
                <svg class="power-on">
                    <use xlink:href="#line" class="line"></use>
                    <use xlink:href="#circle" class="circle"></use>
                </svg>
            </div>
        </div>
	

    </div>
        
    <div class="bouton2">
        
        <p class="textebouton"> Prise Wi-Fi 2</p>

        <div class="power-switch">
            <input type="checkbox">
            <div data-brackets-id="375" class="button">
                <svg data-brackets-id="376" class="power-off">
                    <use data-brackets-id="377" xlink:href="#line" class="line"></use>
                    <use data-brackets-id="378" xlink:href="#circle" class="circle"></use>
                </svg>
                <svg data-brackets-id="379" class="power-on">
                    <use data-brackets-id="380" xlink:href="#line" class="line"></use>
                    <use data-brackets-id="381" xlink:href="#circle" class="circle"></use>
                </svg>
            </div>
        </div>
        
    </div>
        
    <div class="bouton3">
        
        <p class="textebouton"> Prise Wi-Fi 1 & 2</p>

        <div class="power-switch">
            <input type="checkbox">
            <div data-brackets-id="456" class="button">
                <svg data-brackets-id="457" class="power-off">
                    <use data-brackets-id="458" xlink:href="#line" class="line"></use>
                    <use data-brackets-id="459" xlink:href="#circle" class="circle"></use>
                </svg>
                <svg data-brackets-id="460" class="power-on">
                    <use data-brackets-id="461" xlink:href="#line" class="line"></use>
                    <use data-brackets-id="462" xlink:href="#circle" class="circle"></use>
                </svg>
            </div>
        </div>
        
    </div>

</div>
    
    
    
<!-- Image bouton -->
    
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol xmlns="http://www.w3.org/2000/svg" viewBox="0 0 150 150" id="line">
            <line x1="75" y1="34" x2="75" y2="58"></line>
        </symbol>
        
        <symbol xmlns="http://www.w3.org/2000/svg" viewBox="0 0 150 150" id="circle">
            <circle cx="75" cy="80" r="35"></circle>
        </symbol>
    </svg>
    
<!-- Image bouton -->
 
    </body>
    
</html>
