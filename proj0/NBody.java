public class NBody{
    
    /**why we need a static keyword?!!!!*/
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int num = in.readInt();/**???Question! Why this demo cannot run without this line*/
        double radius = in.readDouble();
        return radius;
    }

    /**???*/
    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);/**???*/
        int num = in.readInt();
        double radius = in.readDouble();

        Planet[] planets = new Planet[num];

        for (int i = 0; i < num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel,mass, imgFileName);
            
        }
        return planets;
    }

    public static void main(String[] args) {
        
        /**Collecting all needed input: T/dt/file/planets/radius*/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);/**why we still need a type keyword ahead of a object*/
        double radius = readRadius(filename);

        
        StdDraw.setScale(-radius , radius);/**set bothx and y scale to the same range*/
        StdDraw.clear();/**Clears the screen to the default color*/
        StdDraw.picture(0, 0, "images/starfield.jpg");
        /**draws the specified image centered at(x,y). Only JPEG,PNG and GIF are supported*/

        /**Drawing All of the planets*/
        for (Planet p : planets) {
            p.draw();
        }
        
        StdDraw.enableDoubleBuffering();
        
        double time = 0;
        /** we cannot use for loop here, not right syntax*/
        while ( time <= T ) {
            double[] xForce = new double[planets.length];
            double[] yForce = new double[planets.length];

            for (int i = 0; i < planets.length; i++){
                xForce[i] = planets[i].calcNetForceExertedByX(planets);
                yForce[i] = planets[i].calcNetForceExertedByY(planets);
            }
            
            /**update after all the forces have been calculated and safely stored*/
            for (int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForce[i], yForce[i]);
            }
            
            StdDraw.picture(0, 0, "images/Starfield.jpg");
            
            for (Planet bodies : planets){
                bodies.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            /**why we donnot need a type keyword??????*/
            time += dt;

        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
            
            
        

    }

}