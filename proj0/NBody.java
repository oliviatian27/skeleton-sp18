public class NBody{

   public static double readRadius(String fileName){
       In in=new In(fileName);
       int number=in.readInt();
       double radius=in.readDouble();
       return radius;
   } 

   public static Planet[] readPlanets(String fileName) {
        In in=new In(fileName);
        int number=in.readInt();
        double radius=in.readDouble();
        Planet[] planets= new Planet[5];
        int index=0;
        for(int i=0;i<number;i++){
            double xPos=in.readDouble();
            double yPos=in.readDouble();
            double xVel=in.readDouble();
            double yVel=in.readDouble();
            double mass=in.readDouble();
            String imgFile=in.readString();
            planets[i]=new Planet(xPos,yPos,xVel,yVel,mass,imgFile);
        }
        return planets;
   }

   public static void main(String[] args){
       String imageToDraw = "images/starfield.jpg";
       double T=Double.parseDouble(args[0]);
       double dt=Double.parseDouble(args[1]);
       String filename = args[2];
       double radius=NBody.readRadius(filename);
       Planet[] planets=NBody.readPlanets(filename);

       StdDraw.setScale(-radius,radius);

		/* Clears the drawing window. */
		StdDraw.clear();

        StdDraw.picture(0,0, imageToDraw);
        for(Planet p:planets){
            p.draw();
        }

        StdDraw.enableDoubleBuffering();

        double t=0;
        while(t<=T){
            double[] xForces= new double[planets.length];
            double[] yForces= new double[planets.length];

            for(int i=0;i<planets.length;i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }

            for(int i=0;i<planets.length;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(Planet p:planets){
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
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