import webbrowser
import time
import sys
import pyautogui
 
def open_urls_in_tabs(urls):
    if not urls:
        print("The URL list is empty. Exiting.")
        return
    cleaned_urls = [url.strip() for url in urls]
 
    print("Opening all URLs in new tabs...")
    webbrowser.open(cleaned_urls[0], new=1)
    time.sleep(1)
    for url in cleaned_urls[1:]:
        print(f"Opening: {url}")
        webbrowser.open(url, new=2)
        time.sleep(1)
 
    print("\nAll URLs have been opened in your browser.")
    print("Starting tab cycling in 5 seconds...")
    print("Press Ctrl+C in this terminal to exit.")
    time.sleep(5)
 
    try:
        pyautogui.hotkey('ctrl', '1')
        time.sleep(1)
        while True:
            pyautogui.hotkey('ctrl', 'tab')
            time.sleep(3)
    except KeyboardInterrupt:
        print("\nProgram stopped by user. Goodbye!")
        sys.exit(0)
 
if __name__ == "__main__":
    websites_to_visit = [
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6234/IMG_8325_be3c7b2f2ae30360d86a5c64fb3e27d0.JPG",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6241/ZETA_1_be3c7b2f2ae30360d86a5c64fb3e27d0.JPG",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6240/Zemanuv-atelier_Zlin_zdroj_Zlin-Film-Office_14_be3c7b2f2ae30360d86a5c64fb3e27d0.JPG",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6239/Zemanuv-atelier_Zlin_zdroj_Zlin-Film-Office_12_be3c7b2f2ae30360d86a5c64fb3e27d0.JPG",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6238/Zemanuv-atelier_Zlin_zdroj_Zlin-Film-Office_03_be3c7b2f2ae30360d86a5c64fb3e27d0.JPG",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6237/je%C5%99%C3%A1b_be3c7b2f2ae30360d86a5c64fb3e27d0.jpg",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6236/IMG_20190121_120425_be3c7b2f2ae30360d86a5c64fb3e27d0.jpg",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6235/IMG_9072_be3c7b2f2ae30360d86a5c64fb3e27d0.jpg    ",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6233/IMG_0976_be3c7b2f2ae30360d86a5c64fb3e27d0.JPG",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6231/12240929_912941772123163_7252336715004768009_o_be3c7b2f2ae30360d86a5c64fb3e27d0.jpg",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6232/IMG_0975_be3c7b2f2ae30360d86a5c64fb3e27d0.JPG",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6230/10304870_778179025599439_5772323549510383246_n_be3c7b2f2ae30360d86a5c64fb3e27d0.jpg",
        "https://creativehill.cz/imager/data/galleries/odborne-vybaveni-skoly/gallery/6229/4D_be3c7b2f2ae30360d86a5c64fb3e27d0.jpg",
    ]
 
    open_urls_in_tabs(websites_to_visit)
