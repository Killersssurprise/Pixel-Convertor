# Image pixel converter (Pixel battle converter)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Pixel converter is a  mobile-ready, offline-ready, OpenCV powered Java Image converter which allow you to get any pixelized image.

  - Supports median filtration, erode and dilate methods
  - Use any colors you want
  - Support image resizing


# What it does

Here is an inline 

<img src="https://i.pinimg.com/originals/b8/a8/a2/b8a8a2b3d11ca5a56702899fb0e60028.jpg" alt="text" width="351" height="443"> <img src="src/crusader_output.png" alt="text" width="351" height="443"> <img src="src/crusader_output2.png" alt="text" width="351" height="443">



<img src="src/i1.jpg" alt="text" width="480" height="345">  <img src="src/i1.png" alt="text" width="480" height="345">

<img src="src/i2.jpg" alt="text" width="480" height="300">  <img src="src/i2.png" alt="text" width="480" height="300">



### Tech

Image Pixel Converter itself is open source with a [public repository][dill]
 on GitHub.

### Installation

Image Pixel Converter requires [OpenCV](https://opencv.org/) v4.x.x+ to run.

Install the dependencies and start the app

``` sh
$ java -jar pixelconvertor.jar attrs
```

### Attrs are

```sh
 -width = setup the output image width
 -height = setup the output image height
 Note: If there is no width or height or both output image will be the same size as input one
  -r = red color correction [-255;255]
  -g = green color correction [-255;255]
  -b = blue color [-255;255]
  -median k_size = setup the median filtration with k_size (k_size should be odd)
  -erode k_size = set erode with k_size arg.(k_size should be odd)
  -dilate k_size = set dilate with k_size arg. (k_size should be odd)
 -input = the input image path 
 -output = the output image path
 -colors args = replace default colorw with args values. It should be in hex form (For example "-colors #000000,#FFFFFF,#CDCDCD" #000000 or 000000 no matters)
```
### Running example
```sh
$ java -jar <how_you_call_artifacts_name>.jar -g 20 -dilate 1 -width 260 -height 300 -median 7 -input /home/mixa/crusader.bmp -output /home/mixa/initial_d/crusader.png
```
### Default palette

- ![#FFFFFF](https://placehold.it/15/FFFFFF/000000?text=+) `#FFFFFF`
- ![#C2C2C2](https://placehold.it/15/C2C2C2/000000?text=+) `#C2C2C2`
- ![#858585](https://placehold.it/15/858585/000000?text=+) `#858585`
- ![#474747](https://placehold.it/15/474747/000000?text=+) `#474747`
- ![#000000](https://placehold.it/15/000000/000000?text=+) `#000000`
- ![#3AAFFF](https://placehold.it/15/3AAFFF/000000?text=+) `#3AAFFF`
- ![#71AAEB](https://placehold.it/15/71AAEB/000000?text=+) `#71AAEB`
- ![#4A76A8](https://placehold.it/15/4A76A8/000000?text=+) `#4A76A8`
- ![#074BF3](https://placehold.it/15/3AAFFF/074BF3?text=+) `#074BF3`
- ![#5E30EB](https://placehold.it/15/5E30EB/000000?text=+) `#5E30EB`
- ![#FF6C5B](https://placehold.it/15/FF6C5B/000000?text=+) `#FF6C5B`
- ![#FE2500](https://placehold.it/15/FE2500/074BF3?text=+) `#FE2500`
- ![#FF218B](https://placehold.it/15/FF218B/000000?text=+) `#FF218B`
- ![#99244F](https://placehold.it/15/99244F/000000?text=+) `#99244F`
- ![#4D2C9C](https://placehold.it/15/4D2C9C/074BF3?text=+) `#4D2C9C`
- ![#FFCF4A](https://placehold.it/15/FFCF4A/000000?text=+) `#FFCF4A`
- ![#FEB43F](https://placehold.it/15/99244F/FEB43F?text=+) `#FEB43F`
- ![#FE8648](https://placehold.it/15/FE8648/074BF3?text=+) `#FE8648`
- ![#FF5B36](https://placehold.it/15/FF5B36/000000?text=+) `#FF5B36`
- ![#DA5100](https://placehold.it/15/DA5100/FEB43F?text=+) `#DA5100`
- ![#94E044](https://placehold.it/15/94E044/074BF3?text=+) `#94E044`
- ![#5CBF0D](https://placehold.it/15/5CBF0D/000000?text=+) `#5CBF0D`
- ![#C3D117](https://placehold.it/15/C3D117/FEB43F?text=+) `#C3D117`
- ![#FCC700](https://placehold.it/15/FCC700/074BF3?text=+) `#FCC700`
- ![#D38301](https://placehold.it/15/D38301/000000?text=+) `#D38301`


# Todos:
  - Import and pixelize images from Internet
  - Add more filter methods


License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
