public class Modele {
    private Object controleurActif;
    private Stack<Forme> undoStack = new Stack<>();
    private Stack<Forme> redoStack = new Stack<>();
    private Vue vue;

    public void setControleurActif(Object c) {
        this.controleurActif = c;
        this.vue.setValidationActive(false);
        this.vue.setUndoActive(false);
        this.vue.setRedoActive(false);
    }

    public void setActionsActives(boolean actives) {
    vue.setValidationActive(actives);
    vue.setUndoActive(actives);
    vue.setRedoActive(actives);

    vue.getBouton("Cercle").setEnabled(actives);
    vue.getBouton("Rectangle").setEnabled(actives);
    }

    public Object getControleurActif() {
        return controleurActif;
    }

    public void setVue(Vue vue) {
        this.vue = vue;
    }

    public Vue getVue() {
        return vue;
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Forme derniereForme = undoStack.pop();
            redoStack.push(derniereForme);
            formes.remove(derniereForme);
            notifierObservers();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Forme derniereForme = redoStack.pop();
            formes.add(derniereForme);
            undoStack.push(derniereForme);
            notifierObservers();
        }
    }

    public void valider(){

    }

    public void quitter(){
        
    }
}