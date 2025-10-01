import webbrowser
import time
import sys
 
def open_urls_in_tabs(urls):
    """Opens a list of URLs, each in a new tab."""
    if not urls:
        print("The URL list is empty. Exiting.")
        return
 
    print("Opening all URLs in new tabs...")
    webbrowser.open(urls[0], new=1)
    for url in urls[1:]:
        print(f"Opening: {url}")
        webbrowser.open(url, new=2)
        time.sleep(1)
 
    print("\nAll URLs have been opened in your browser.")
    print("The script will now wait. Press Ctrl+C in this terminal to exit.")
 
    try:
        while True:
            time.sleep(1)
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
