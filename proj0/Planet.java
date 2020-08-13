public class Planet {
    
    /**declaring variables of Planet class
     * Planet class have 6 non-static fields
     * Each instance of Planet class can have those parameters
     * the first constructor takes six arguments, then initializes these fields for all new Planet object
     * 
     */
    public double xxPos;
    public double yyPos;
    public double xxVel;/*current velocity in x direction*/
    public double yyVel;/*its current velocity in the y direction*/
    public double mass; /*Its mass*/
    public String imgFileName;/*The name of the file that corresponds to the image that depicts the planet */
    public static final double g = 6.67e-11;/**the constant of gravity*/

    /**First constructor of a planet class, initializing an instance  
     * why do not we need a type keyword ahead of */
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**This constructor takes in another Planet as its only argument
     * Namely: copy a Planet object;
    */
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){/**Notice the return type double*/
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double r = Math.hypot(dx, dy);
        return r;
    }

    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        double f = this.mass * p.mass * Planet.g / Math.pow(r, 2);
        return f;
    }

    public double calcForceExertedByX(Planet p){
        double r = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        return f * dx / r;
    }

    public double calcForceExertedByY(Planet p){
        double r = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        return f * dy / r;
    }

    /**Calculate the net force in x direction and y direction*/
    public double calcNetForceExertedByX(Planet[] ps){
        /*Planet[] ps = {}*/
        double netforceX = 0;
       
        /**"!" means logically  */
        for (Planet p : ps){
            if(!this.equals(p)){
                netforceX += this.calcForceExertedByX(p);
            }
        }
        return netforceX;

    }  

    public double calcNetForceExertedByY(Planet[] ps){
        /*Planet[] ps = {}*/
        double netforceY = 0;

        for (Planet p : ps){
            if(!this.equals(p)){
                netforceY += this.calcForceExertedByY(p);
            }
        }
        return netforceY;

    }

    /**here we do not need a "double" type keyword, because we do not need to return a single value*/
    public void update(double dt, double fx, double fy){
        double accX = fx / this.mass;
        double accY = fy / this.mass;
        this.xxVel += accX * dt;
        this.yyVel += accY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;

    }


    /**this method should return nothing and take in no parameters,
     * so  we do not need a constructor*/
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        
    }



}